package com.example.cata.tabs.imageviewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cata.tabs.R;
import com.example.cata.tabs.imageviewpager.CustomPagerAdapter;
import com.example.cata.tabs.imageviewpager.DataModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomPagerAdapter adapter = new CustomPagerAdapter(this, getDataList());

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(adapter);
        pager.setPageTransformer(true, new ZoomOut());
    }

    public List<DataModel> getDataList(){

        List<DataModel> itemList = new ArrayList<>();

        int[] imageId = new int[]{
                R.drawable.img1, R.drawable.img2, R.drawable.img3,
                R.drawable.img4, R.drawable.img5, R.drawable.img6
        };

        String [] titles = new String[]{
                "img 1", "img 2", "img3", "img4", "img5", "img6"
        };

        for (int i = 0;i < imageId.length;i ++)
            itemList.add(new DataModel(titles[i], imageId[i]));

        return itemList;
    }

}
