package com.example.cata.tabs.fragmentsviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.cata.tabs.R;
import com.example.cata.tabs.fragmentsviewpager.fragments.FragmentFour;
import com.example.cata.tabs.fragmentsviewpager.fragments.FragmentOne;
import com.example.cata.tabs.fragmentsviewpager.fragments.FragmentThree;
import com.example.cata.tabs.fragmentsviewpager.fragments.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cata on 14.03.2017.
 */

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragments);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Simple Tabs Example");
        setSupportActionBar(toolbar);

        prepareDataResourse();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        CustomAdrapter customAdrapter = new CustomAdrapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(customAdrapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void prepareDataResourse() {

        addData(new FragmentOne(), "ONE");
        addData(new FragmentTwo(), "TWO");
        addData(new FragmentThree(), "THREE");
        addData(new FragmentFour(), "FOUR");
        addData(new FragmentOne(), "FIVE");
        addData(new FragmentThree(), "SIX");
        addData(new FragmentFour(), "SEVEN");
    }

    private void addData(Fragment fragment, String title) {
        fragmentList.add(fragment);
        titleList.add(title);
    }
}
