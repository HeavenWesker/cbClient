package com.catchingnow.cbclient;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    String[]              slideContent;
    ListView              slideLayout;
    ArrayAdapter<String>  drawerAdapter;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout          drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideContent = getResources().getStringArray(R.array.dummy_slide_content);
        slideLayout = (ListView) findViewById(R.id.slideLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, slideContent);
        slideLayout.setAdapter(drawerAdapter);
        slideLayout.setOnItemClickListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.string.drawer_layout_open,
                R.string.drawer_layout_close);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
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
//        getActionBar().setTitle(slideContent[position]);
        ItemClick(position);
    }

    public void ItemClick(int position){
        setTitle(slideContent[position]);
        drawerLayout.closeDrawer(Gravity.START);
    }
}
