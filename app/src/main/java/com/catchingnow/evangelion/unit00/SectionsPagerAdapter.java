package com.catchingnow.evangelion.unit00;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.support.v13.app.FragmentPagerAdapter;

import com.catchingnow.cbclient.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * Created by Heaven on 3/19/15.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    List<Map<String, Object>> list = new ArrayList<>();
    //TODO Change this with real data
    private ArrayList<Page> pages = new ArrayList<>();
    public Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        this(fm);
        this.context = context;
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor cursor = context.getContentResolver().query(ArticleContentProvider.CONTENT_URI, projection,
                selection, selectionArgs, sortOrder);
        while (!cursor.isLast()){
            cursor.moveToNext();
            Map<String, Object> item = new HashMap<>();
            item.put("date", cursor.getLong(cursor.getColumnIndex(ArticleDBContract.ArticleInfo.COLUMN_NAME_DATE)));
            item.put("title", cursor.getString(cursor.getColumnIndex(ArticleDBContract.ArticleInfo.COLUMN_NAME_TITLE)));
            item.put("content", cursor.getString(cursor.getColumnIndex(ArticleDBContract.ArticleInfo.COLUMN_NAME_CONTENT)));
            item.put("link", cursor.getString(cursor.getColumnIndex(ArticleDBContract.ArticleInfo.COLUMN_NAME_LINK)));
            item.put("other", cursor.getString(cursor.getColumnIndex(ArticleDBContract.ArticleInfo.COLUMN_NAME_OTHER)));
            list.add(item);
        }
        for (int i = 0; i < 2; i++) {
            Page page;
            if (i == 0){
                page = new Page(Page.PAGE_TYPE_LIST);
                page.setPageTitle(context.getResources().getString(R.string.time_line));
            }else/* if (i == 1)*/{
                page = new Page(Page.PAGE_TYPE_DETIAL);
                page.setPageTitle(context.getResources().getString(R.string.detial));
            }
            pages.add(page);
        }
    }

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (pages.get(position).getPAGE_TYPE()){
            case Page.PAGE_TYPE_LIST:
                String [] section = {ArticleDBContract.ArticleInfo.COLUMN_NAME_DATE,
                        ArticleDBContract.ArticleInfo.COLUMN_NAME_TITLE};
                SectionsPagerFragment sectionsPagerFragment = new SectionsPagerFragment(new ArticleAdapter(
                        context, list, R.layout.card_view, new String[]{"date", "title"/*, "content","link"*/}, new int[]{R.id.date,R.id.title})
                );
                return sectionsPagerFragment;
            default:
                return new Fragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pages.get(position).getPageTitle();
    }

    @Override
    public int getCount() {
        return pages.size();
    }

}
