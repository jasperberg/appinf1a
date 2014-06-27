package com.DDB.hardwire.app;

/**
 * Created by MarK on 11-Jun-14.
 * The fragment manager for the guides.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        for(int i = 0; i < Guides.getTextsSize(); i++){
            if(i == index){
                return new Guides_page(index);
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return Guides.getTextsSize();
    }

}