package com.example.cata.tabs.fragmentsviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cata.tabs.fragmentsviewpager.fragments.FragmentFour;
import com.example.cata.tabs.fragmentsviewpager.fragments.FragmentOne;
import com.example.cata.tabs.fragmentsviewpager.fragments.FragmentThree;
import com.example.cata.tabs.fragmentsviewpager.fragments.FragmentTwo;

import java.util.List;

/**
 * Created by cata on 14.03.2017.
 */

public class CustomAdrapter extends FragmentPagerAdapter {
    private static final int ITEMS = 4;

    private List<Fragment> fragments;
    private List<String> titleList;

    public CustomAdrapter(FragmentManager fm, List<Fragment> fragments, List<String> titleList) {
        super(fm);
        this.fragments = fragments;
        this.titleList = titleList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
