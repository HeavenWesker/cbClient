package com.catchingnow.evangelion.unit00;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Heaven on 4/22/15.
 */
public class ArticleDBContract {
    public ArticleDBContract(){}
    public static abstract class ArticleInfo implements BaseColumns {
        public static final String TABLE_NAME                   = "user_info";
        public static final String COLUMN_NAME_ID           ="_id";
        public static final String COLUMN_NAME_DATE          = "date";
        public static final String COLUMN_NAME_TITLE        = "title";
        public static final String COLUMN_NAME_CONTENT    = "content";
        public static final String COLUMN_NAME_SOURCE       = "source";
        public static final String COLUMN_NAME_LINK         = "LINK";
    }
    private static final String TEXT_TYPE           = " TEXT";
    private static final String INT_TYPE            = " INTEGER";
    private static final String COMMA_SEP           = ",";
    public static final String SQL_CREATE_TABLE     =
            "CREATE TABLE " + ArticleInfo.TABLE_NAME       + "(" +
                    ArticleInfo.COLUMN_NAME_ID        + " INTEGER PRIMARY KEY," +
                    ArticleInfo.COLUMN_NAME_DATE      + TEXT_TYPE + COMMA_SEP +
                    ArticleInfo.COLUMN_NAME_TITLE  + TEXT_TYPE + COMMA_SEP +
                    ArticleInfo.COLUMN_NAME_CONTENT     + TEXT_TYPE + COMMA_SEP +
                    ArticleInfo.COLUMN_NAME_SOURCE       + TEXT_TYPE  + COMMA_SEP +
                    ArticleInfo.COLUMN_NAME_LINK       + TEXT_TYPE  +
                    ")";
    public static final String SQL_DROP_TABLE    =
            "DROP TABLE " + ArticleInfo.TABLE_NAME;
    public static final String SQL_SCHEA = ".schema";

}
