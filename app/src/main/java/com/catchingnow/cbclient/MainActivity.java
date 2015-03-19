package com.catchingnow.cbclient;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    String[] pagerTitles;// = getResources().getStringArray(R.array.page_titles);

    String[]              slideContent;
    ListView              slideLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout          drawerLayout;
    ViewPager       viewPager;
    PagerTabStrip   pagerTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);

        pagerTitles = getResources().getStringArray(R.array.page_titles);
        slideContent = getResources().getStringArray(R.array.dummy_slide_content);
        slideLayout = (ListView) findViewById(R.id.slideLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        slideLayout.setAdapter(new MyAdapter(this));
        slideLayout.setOnItemClickListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.string.drawer_layout_open,
                R.string.drawer_layout_close);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        drawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        viewPager = (ViewPager) findViewById(R.id.viewPager);
//        viewPager.setPadding(0, 24+56+72, 0, 0);
        viewPager.setAdapter(new SectionsPagerAdapter(getFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return pagerTitles[position];
            }

            @Override
            public int getCount() {
                return pagerTitles.length;
            }

        });
//        ActionBar actionBar = getActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        for (String pagerTitle:pagerTitles) {
//            actionBar.addTab(actionBar.newTab().setText(pagerTitle));
//        }
        pagerTabStrip = (PagerTabStrip) findViewById(R.id.tab);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0){
            ItemClick(position - 1);
        }
    }

    public void ItemClick(int position){
        setTitle(slideContent[position]);
        drawerLayout.closeDrawer(Gravity.START);
    }
    class MyAdapter extends BaseAdapter{
        String[] catalogs;
        Context context;
        int[] images = {
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
        };

        public MyAdapter(Context context){
            this.context = context;
            catalogs = getResources().getStringArray(R.array.dummy_slide_content);
        }

        @Override
        public int getCount() {
            return slideContent.length+1;
        }

        @Override
        public Object getItem(int position) {
            return catalogs[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (position == 0){
                View row = null;
                if (convertView == null){
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                    row = inflater.inflate(R.layout.title_image, parent, false);
                }else {
                    row = convertView;
                }
//                ImageView titleImage = (ImageView) row.findViewById(R.id.title_image);
//                titleImage.setImageResource(R.mipmap.title_image);
                return row;
            }else {
                return getRolView(position-1 , convertView, parent);
            }
        }
        public View getRolView(int position, View convertView, ViewGroup parent) {
            View row = null;
            if (convertView == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.custom_rol, parent, false);
            }else {
                row = convertView;
            }
            TextView catalogTextView = (TextView) row.findViewById(R.id.catalogTitle);
            catalogTextView.setText(catalogs[position]);
            ImageView catalog_icon = (ImageView) row.findViewById(R.id.catalogIcon);
            if (position >= images.length){
                catalog_icon.setImageResource(images[0]);
                Log.d("WRONG", "WRONG");
            }else {
                catalog_icon.setImageResource(images[position]);
                Log.d("OK", "OK");
            }
            return row;
        }
    }
}
