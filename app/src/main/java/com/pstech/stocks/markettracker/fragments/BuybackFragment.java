package com.pstech.stocks.markettracker.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pstech.stocks.markettracker.R;
import com.pstech.stocks.markettracker.fragments.buybackfragments.CurrentUpcomingBBFragment;
import com.pstech.stocks.markettracker.fragments.buybackfragments.NewsBBFragment;
import com.pstech.stocks.markettracker.fragments.buybackfragments.PastBBFragment;
import com.pstech.stocks.markettracker.fragments.buybackfragments.TrackerBBFragment;

/**
 * Created by pagrawal on 06-05-2018.
 */

public class BuybackFragment extends AppFragment {


    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    public static BuybackFragment newInstance(String param1) {

        Bundle args = new Bundle();

        BuybackFragment fragment = new BuybackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(), "Not Implemented Yet", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_ipo_fragment, container, false);
//        Toast.makeText(getContext(), "Not Implemented Yet", Toast.LENGTH_SHORT).show();

        final Fragment[] mFragments = new Fragment[] {
                new CurrentUpcomingBBFragment(),
                new PastBBFragment(),
                new NewsBBFragment(),
                new TrackerBBFragment(),
        };

        final String[] mFragmentNames = new String[] {
                getString(R.string.heading_recent),
                getString(R.string.heading_past),
                getString(R.string.heading_news),
                getString(R.string.heading_tracker)
        };

        // Set up the ViewPager with the sections adapter.
        mViewPager = view.findViewById(R.id.container);
        setupTabs(view, mFragments, mFragmentNames, mPagerAdapter);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        return view;
    }
}
