package com.ryan.recap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainFragmentStatePagerAdapter extends FragmentActivity {

    private static final String HOME = "Home";
    private static final String SCHOOLS = "Schools";

    private ActionBar theActionBar;
    private ViewPager thePager;
    private Context theC;
    private FragmentAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment_state_pager_adapter);

        this.theC = this;
        this.theActionBar = getActionBar();
        this.myAdapter = new FragmentAdapter(getSupportFragmentManager(), getFragments());
        this.thePager = (ViewPager) findViewById(R.id.viewpager);

        this.thePager.setOnPageChangeListener(thePageListener);
        this.thePager.setAdapter(this.myAdapter);
        this.theActionBar.setDisplayShowTitleEnabled(true);

        final Tab homeTab = theActionBar.newTab().setText(HOME).setTabListener(tabListener);
        theActionBar.addTab(homeTab, 0);

        final Tab schoolsTab = theActionBar.newTab().setText(SCHOOLS).setTabListener(tabListener);
        theActionBar.addTab(schoolsTab, 1);
    }

    private final ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            thePager.setCurrentItem(tab.getPosition());

            switch (tab.getPosition()) {
                case 0:
                    theActionBar.setTitle("Home");
                    break;
                case 1:
                    theActionBar.setTitle("Schools");
                    break;
                default:
                    theActionBar.setTitle("Recap");
                    break;
            }
        }

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {

        }
    };

    private List<Fragment> getFragments() {
        final List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new Home_Fragment());
        fragments.add(new Schools_Fragment());
        return fragments;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_fragment_state_pager_adapter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class FragmentAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> fragments;

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;

        }

        @Override
        public Fragment getItem(final int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }

    //listener for pageChange
    final ViewPager.SimpleOnPageChangeListener thePageListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            theActionBar.setSelectedNavigationItem(position);
        }

        int positionCurrent;
        boolean dontLoadList;

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == 0) { // the viewpager is idle as swipping ended
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (!dontLoadList) {
                            //async thread code to execute loading the list...
                        }
                    }
                }, 06);
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            positionCurrent = position;
            if (positionOffset == 0 && positionOffsetPixels == 0) {
                // the offset is zero when the swiping ends{
                dontLoadList = false;
            }
            else {
                // To avoid loading content for list after swiping the pager.
                dontLoadList = true;
            }
        }
    };
}
