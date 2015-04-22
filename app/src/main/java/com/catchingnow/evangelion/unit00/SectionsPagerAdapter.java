package com.catchingnow.evangelion.unit00;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

import com.catchingnow.cbclient.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Heaven on 3/19/15.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    //TODO Change this with real data
    public List<Map<String, Object>> listItems = new ArrayList<>();
    public Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context){
        this(fm);
        this.context = context;
    }
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
        for (int i = 0; i < 40; i++){
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("title", "The 1th title");
            listItem.put("image", R.mipmap.ic_launcher);
            listItems.add(listItem);
        }
    }

    @Override
    public Fragment getItem(int position) {
        ArticleItemFragment articleItemFragment = new ArticleItemFragment();
        return articleItemFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
