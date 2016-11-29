package com.example.cata.customlistview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cata.customlistview.CustomList;
import com.example.cata.customlistview.R;
import com.example.cata.customlistview.models.DayData;
import com.squareup.picasso.Picasso;

import java.util.List;

import static java.lang.System.load;

/**
 * Created by Cata on 11/28/2016.
 */

public class ElementsAdapter  extends RecyclerView.Adapter<ElementsAdapter.MyViewHolder>{

    private List<DayData> elementsList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView dayDate;
        private TextView dayKilometers;

        private ImageView dayFlag, smileIcon, roadIcon, sunIcon;

        private ImageView avTimerIcon;
        private TextView timeVsKm;

        private ImageView timerIcon;
        private TextView timeValue;

        public MyViewHolder(View view) {
            super(view);

            this.dayDate = (TextView) view.findViewById(R.id.date);
            this.dayKilometers = (TextView) view.findViewById(R.id.kilometers);

            this.dayFlag = (ImageView) view.findViewById(R.id.flag);
            this.smileIcon = (ImageView) view.findViewById(R.id.smileIcon);
            this.roadIcon = (ImageView) view.findViewById(R.id.roadIcon);
            this.sunIcon = (ImageView) view.findViewById(R.id.sunIcon);

            this.avTimerIcon = (ImageView) view.findViewById(R.id.avTimer);
            this.timeVsKm = (TextView) view.findViewById(R.id.timeVsKm);

            this.timerIcon = (ImageView) view.findViewById(R.id.timerIcon);
            this.timeValue = (TextView) view.findViewById(R.id.timeValue);
        }
    }

    public ElementsAdapter(List<DayData> dayData, Context context){
        this.elementsList = dayData;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DayData dayData = elementsList.get(position);

        holder.dayDate.setText(dayData.getDate());
        holder.dayKilometers.setText(dayData.getKilometers());

        holder.dayFlag.setImageResource(R.drawable.flag_variant);
        holder.smileIcon.setImageResource(R.drawable.smaile_icon);
        holder.roadIcon.setImageResource(R.drawable.road_variant);

        Picasso.with(context).load("http://lorempixel.com/150/150/").placeholder(R.drawable.sun_icon).into(holder.sunIcon);

        holder.avTimerIcon.setImageResource(R.drawable.av_timer);
        holder.timeVsKm.setText(dayData.getTimeVsKm());

        holder.timerIcon.setImageResource(R.drawable.timer);
        holder.timeValue.setText(dayData.getTime());
    }

    @Override
    public int getItemCount() {
        return elementsList.size();
    }

}
