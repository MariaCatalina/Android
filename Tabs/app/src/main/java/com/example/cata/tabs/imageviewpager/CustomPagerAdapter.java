package com.example.cata.tabs.imageviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cata.tabs.R;

import java.util.List;

/**
 * Created by cata on 14.03.2017.
 */

public class CustomPagerAdapter extends PagerAdapter {

    private Context context;
    private List<DataModel> itemsList;

    private LayoutInflater inflater;

    public CustomPagerAdapter(Context context, List<DataModel> itemsList) {
        this.context = context;
        this.itemsList = itemsList;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = inflater.inflate(R.layout.viewpager_item, container, false);

        ImageView image = (ImageView) view.findViewById(R.id.imageItem);
        TextView textView = (TextView) view.findViewById(R.id.textViewItem);

        image.setImageResource(itemsList.get(position).getImageId());
        textView.setText(itemsList.get(position).getTitle());

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((FrameLayout) object);

    }
}
