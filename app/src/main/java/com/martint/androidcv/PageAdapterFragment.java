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
        switch (i) {
            case 0:
                return new HomeFragment();
            case 1:
                return new AboutMeFragment();
            case 2:
                return new EducationFragment();
            case 3:
                return new QualificationsFragment();
            case 4:
                return new ComputingProjectFragment();
            case 5:
                return new EmploymentFragment();
            default:
                return new InterestsFragment();
        }
    }

    @Override
    public int getCount() {
        return 7;
    }
}