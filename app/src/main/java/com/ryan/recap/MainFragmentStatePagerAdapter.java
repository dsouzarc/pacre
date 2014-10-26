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
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentStatePagerAdapter extends FragmentActivity {

    private static final String FEED = "Feed";
    private static final String RANKINGS = "Rankings";
    private static final String NEW_RECAP = "New Recap";
    private static final String NOTIFICATIONS = "Notifications";
    private static final String PROFILE = "Profile";

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
        this.thePager.setAdapter(this.myAdapter);
        this.thePager.setOnPageChangeListener(thePageListener);

        this.theActionBar.show();
        this.theActionBar.setDisplayShowTitleEnabled(true);
        this.theActionBar.setHomeButtonEnabled(false);
        this.theActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        final Tab feedTab = theActionBar.newTab().setText(FEED).setTabListener(tabListener);
        theActionBar.addTab(feedTab, 0);

        final Tab rankingsTab = theActionBar.newTab().setText(RANKINGS).setTabListener(tabListener);
        theActionBar.addTab(rankingsTab, 1);

        final Tab newRecapTab = theActionBar.newTab().setText(NEW_RECAP).setTabListener(tabListener);
        theActionBar.addTab(newRecapTab, 2);

        final Tab notificationsTab = theActionBar.newTab().setText(NOTIFICATIONS).setTabListener(tabListener);
        theActionBar.addTab(notificationsTab, 3);

        final Tab profileTab = theActionBar.newTab().setText(PROFILE).setTabListener(tabListener);
        theActionBar.addTab(profileTab, 4);

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
        fragments.add(new Feed_Fragment());
        fragments.add(new Rankings_Fragment());
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

        @Override
        public CharSequence getPageTitle(int position) {
            //return CONTENT[position % CONTENT.length].toUpperCase();
            return "Page Title: " + position;
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
