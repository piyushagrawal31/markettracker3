package com.pstech.stocks.markettracker.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by pagrawal on 04-12-2017.
 */

public class AppFragment extends Fragment {

    protected void setupTabs(View view, final Fragment[] mFragments,
                           final String[] mFragmentNames,
                           FragmentPagerAdapter mPagerAdapter) {

        mPagerAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mFragments.length;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };

    }
}
