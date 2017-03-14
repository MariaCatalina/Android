package com.example.cata.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.cata.tabs.tabstextandicons.TextAndIcon;


/**
 * Created by cata on 14.03.2017.
 */

public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
    }

    public void showImages(View view) {
        Intent intent = new Intent(this, com.example.cata.tabs.imageviewpager.MainActivity.class);
        startActivity(intent);
    }

    public void showTabs(View view) {
        Intent intent = new Intent(this, com.example.cata.tabs.fragmentsviewpager.MainActivity.class);
        startActivity(intent);
    }

    public void showTabsWithTextAndIcon(View view) {
        Intent intent = new Intent(this, TextAndIcon.class);
        startActivity(intent);
    }
}
