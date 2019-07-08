package com.martint.androidcv;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Provides the appropriate fragment for the view pager.
 */
public class PageAdapterFragment extends FragmentPagerAdapter {


    public PageAdapterFragment(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return CVFragment.newInstance(i);
    }

    @Override
    public int getCount() {
        return 7;
    }
}