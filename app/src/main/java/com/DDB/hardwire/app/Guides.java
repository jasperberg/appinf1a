package com.DDB.hardwire.app;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.app.ActionBar;

import com.DDB.R;
import android.app.ActionBar.Tab;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by MarK on 25-Jun-14.
 */
public class Guides extends FragmentActivity implements
        ActionBar.TabListener {

    private ViewPager viewPager;
    private PagerAdapter mAdapter;
    private ActionBar actionBar;
    public static List<GuidePage> pageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guides);
        pageList = GetGuidePages.getPageList();

        int id = getIntent().getIntExtra("id", 0);

        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (int i = 0; i < pageList.size(); i++){
            String tab_name = pageList.get(i).getTabName();
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        if(id == 0) {
            viewPager.setCurrentItem(0);
            actionBar.setSelectedNavigationItem(0);
        }

        if(id == 1) {
            viewPager.setCurrentItem(3);
            actionBar.setSelectedNavigationItem(3);
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

    public static int getTabsSize(){
        return pageList.size();
    }

    public static int getTextsSize(){
        return pageList.size();
    }

    public static String getTabById(int id){
        return pageList.get(id).getTabName();
    }

    public static String getTextById(int id){
        return pageList.get(id).getText();
    }

}