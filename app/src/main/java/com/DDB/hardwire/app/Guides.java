package com.DDB.hardwire.app;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.app.ActionBar;

import com.DDB.R;
import android.app.ActionBar.Tab;


public class Guides extends FragmentActivity implements
        ActionBar.TabListener {

    private ViewPager viewPager;
    private PagerAdapter mAdapter;
    private ActionBar actionBar;
    public static String[] tabs = {"Welkom", "Componenten selecteren", "Klaar met de componenten", "Beginnen met bouwen" };
    public static String[] texts = {"Welkom, in deze guide vertellen wij hoe je je eigen computer in elkaar kunt zetten. Je kunt deze guide er gewoon bij houden tijdens het bouwen van jouw computer.\n \nWij wensen u namens de Databazen en HardWire heel veel plezier bij het bouwen van jouw computer!",
                                    "Bij het maken van jouw eigen computer is het belangrijk dat je de juiste componenten selecteerd. In onze app zijn er verschillende onderdeel categorieen beschikbaar, deze kun je selecteren in het menu rechts bovenin tijdens de componenten selecteren. Wanneer je een categorie geselecteerd hebt, zal het onderdeel dat hier het meeste bij hoort automatisch rood worden.",
                                    "Als je klaar bent met selecteren van de componenten en je weet zeker dat alle benodigde onderdelen aanwezig zijn (minimaal een per categorie) en je hebt je computer besteld via de goedkoopste website, kun je aan de slag gaan met het in elkaar zetten van jouw computer",
                                    "Laten we beginnen!"
                                    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guides);

        int id = getIntent().getIntExtra("id", 0);

        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (String tab_name : tabs) {
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
        return tabs.length;
    }

    public static int getTextsSize(){
        return texts.length;
    }

    public static String getTabById(int id){
        return tabs[id];
    }

    public static String getTextById(int id){
        return texts[id];
    }

}