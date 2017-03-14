package com.example.cata.tabs.tabstextandicons;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.cata.tabs.R;
import com.example.cata.tabs.fragmentsviewpager.CustomAdrapter;
import com.example.cata.tabs.fragmentsviewpager.fragments.FragmentFour;
import com.example.cata.tabs.fragmentsviewpager.fragments.FragmentOne;
import com.example.cata.tabs.fragmentsviewpager.fragments.FragmentThree;
import com.example.cata.tabs.fragmentsviewpager.fragments.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

public class TextAndIcon extends AppCompatActivity {

    private Toolbar toolbar;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_and_icon);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Simple Tabs Example");
        setSupportActionBar(toolbar);

        prepareDataResourse();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        CustomAdrapter customAdrapter = new CustomAdrapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(customAdrapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setTabsIcons();
    }


    private void prepareDataResourse() {

        addData(new FragmentOne(), "ONE");
        addData(new FragmentTwo(), "TWO");
        addData(new FragmentThree(), "THREE");
        addData(new FragmentFour(), "FOUR");
    }

    private void addData(Fragment fragment, String title) {
        fragmentList.add(fragment);
        titleList.add(title);
    }


    private void setTabsIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.cloud);
        tabLayout.getTabAt(1).setIcon(R.drawable.love);
        tabLayout.getTabAt(2).setIcon(R.drawable.emoticon);
        tabLayout.getTabAt(3).setIcon(R.drawable.trending);
    }
}
