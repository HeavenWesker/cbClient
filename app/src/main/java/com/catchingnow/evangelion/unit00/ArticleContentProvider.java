package com.catchingnow.evangelion.unit00;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by Heaven on 3/24/15.
 */
public class ArticleContentProvider extends ContentProvider {

    public static Uri CONTENT_URI;
    public static final int uriCode_ALL = 0, uriCode_SINGLE_ROW = 1;
    public static UriMatcher uriMatcher;
    public static final String      TABLE_NAME, AUTHORITY, URL,
            BROAD_CAST_MESSAGE_DATABASE_UPDATED, MESSAGE_TYPE, MESSAGE_TYPE_INSERT, MESSAGE_TYPE_DELETE,
    IDs;
    public static final IntentFilter intentFilter;
    public static final ArticleDBContract.ArticleInfo articleInfo;
    private LocalBroadcastManager localBroadcastManager;

    static {
        articleInfo = new ArticleDBContract.ArticleInfo();
        AUTHORITY = "com.catchingnow.evangelion.unit00.provider";
        TABLE_NAME = articleInfo.TABLE_NAME;
        URL = "content://" + AUTHORITY + "/" + TABLE_NAME;
        CONTENT_URI = Uri.parse(URL);
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, TABLE_NAME, uriCode_ALL);
        uriMatcher.addURI(AUTHORITY, TABLE_NAME + "/#", uriCode_SINGLE_ROW);
        BROAD_CAST_MESSAGE_DATABASE_UPDATED = "BROAD_CAST_MESSAGE_DATABASE_UPDATED";
        intentFilter = new IntentFilter(BROAD_CAST_MESSAGE_DATABASE_UPDATED);
        MESSAGE_TYPE = "MESSAGE_TYPE";
        MESSAGE_TYPE_INSERT = "MESSAGE_TYPE_INSERT";
        MESSAGE_TYPE_DELETE = "MESSAGE_TYPE_DELETE";
        IDs = "_ids";
    }

    ArticleDBHelper articleDBHelper;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        articleDBHelper = new ArticleDBHelper(getContext());
        db = articleDBHelper.getWritableDatabase();
        if (db != null){
            localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d("QUERY", "QUERY ENTER");
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(articleInfo.TABLE_NAME);
        switch (uriMatcher.match(uri)){
            case uriCode_SINGLE_ROW:
            case uriCode_ALL:{
                Cursor cursor = qb.query(
                        db, projection, selection, selectionArgs, null/*groupBy*/,
                        null/*having*/, sortOrder);
                return cursor;
            }default:{
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case uriCode_ALL:{
                return "vnd.android.cursor.dir/vnd."+AUTHORITY+"."+TABLE_NAME;
            } case uriCode_SINGLE_ROW:{
                return "vnd.android.cursor.item/vnd."+AUTHORITY+"."+TABLE_NAME;
            } default:{
                throw  new IllegalArgumentException("Unknown URI" +uri);
            }
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d("INSERT", "START");
        long rowID = db.insert(TABLE_NAME, null, values);
        if (rowID > 0){
            localBroadcastManager.sendBroadcast(new Intent(BROAD_CAST_MESSAGE_DATABASE_UPDATED)
                            .putExtra(MESSAGE_TYPE, MESSAGE_TYPE_INSERT)
                            .putExtra(articleInfo.COLUMN_NAME_ID, rowID));
            return ContentUris.withAppendedId(CONTENT_URI, rowID);
        }else {
            return null;//WTF TODO ERROR HAPPENED
        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count;
        switch (uriMatcher.match(uri)){
            case uriCode_SINGLE_ROW:
            case uriCode_ALL:{
                Cursor cursor = db.query(TABLE_NAME, new String[]{articleInfo.COLUMN_NAME_ID}, selection, selectionArgs, null, null, null);
                ArrayList<Integer> values = new ArrayList<>();
                while (cursor.moveToNext()){
                    values.add(cursor.getInt(cursor.getColumnIndex(articleInfo.COLUMN_NAME_ID)));
                }
                count = db.delete(TABLE_NAME, selection, selectionArgs);
                localBroadcastManager.sendBroadcast(new Intent().setAction(BROAD_CAST_MESSAGE_DATABASE_UPDATED)
                        .putExtra(MESSAGE_TYPE, MESSAGE_TYPE_DELETE)
                        .putExtra(IDs, values));
                break;
            } default:{
                throw  new IllegalArgumentException("Unknown URI" +uri);
            }
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
