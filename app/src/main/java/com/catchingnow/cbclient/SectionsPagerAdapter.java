package com.catchingnow.cbclient;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Heaven on 3/19/15.
 */
public abstract class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = PlaceholderFragment.newInstance(position+1);
        return fragment;
    }

    @Override
    public abstract CharSequence getPageTitle(int position);

    @Override
    public abstract int getCount();

    public static class PlaceholderFragment extends android.app.Fragment {
        public PlaceholderFragment(){

        }
        public static PlaceholderFragment newInstance(int insertPosition){
            PlaceholderFragment fragment = new PlaceholderFragment();
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            return super.onCreateView(inflater, container, savedInstanceState);
            View pagerView = inflater.inflate(R.layout.pager_view, container, false);
            return pagerView;
        }
    }
}
