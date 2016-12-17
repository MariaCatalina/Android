package com.example.cata.clientweather.weatherapp.adaptors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cata.clientweather.R;
import com.example.cata.clientweather.weatherapp.models.AllDaysData;

import java.util.List;

/**
 * Created by Cata on 12/10/2016.
 */

public class AllDaysAdaptor extends RecyclerView.Adapter<AllDaysAdaptor.Holder> {

    private List<AllDaysData> listData;

    public AllDaysAdaptor(List<AllDaysData> listData) {
        super();
        this.listData = listData;
    }

    public static class Holder extends RecyclerView.ViewHolder{

        private TextView dayName;
        private ImageView icon;
        private TextView tempMin;
        private TextView tempMax;

        public Holder(View itemView) {
            super(itemView);
            this.dayName = (TextView) itemView.findViewById(R.id.dayName);
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
            this.tempMin = (TextView) itemView.findViewById(R.id.tempMin);
            this.tempMax = (TextView) itemView.findViewById(R.id.tempMax);
        }
    }

    @Override
    public AllDaysAdaptor.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_days,parent,false);
        return new AllDaysAdaptor.Holder(itemView);
    }

    @Override
    public void onBindViewHolder(AllDaysAdaptor.Holder holder, int position) {
        holder.dayName.setText(listData.get(position).getDayName());

        holder.tempMin.setText(listData.get(position).getTempMin());
        holder.tempMax.setText(listData.get(position).getTempMax());

        boolean setImage = false;
        if (listData.get(position).getDescription().contains("Clouds")) {
            holder.icon.setImageResource(R.drawable.clouds_24);
            setImage = true;
        }

        if (listData.get(position).getDescription().contains("Rain")) {
            holder.icon.setImageResource(R.drawable.rain_24);
            setImage = true;
        }

        if (listData.get(position).getDescription().contains("Clear")) {
            setImage = true;
            holder.icon.setImageResource(R.drawable.sun_24);
        }

        if (listData.get(position).getDescription().contains("light rain")) {
            setImage = true;
            holder.icon.setImageResource(R.drawable.rain_24);
        }

        if (!setImage){
            holder.icon.setImageResource(R.drawable.bright_moon_24);
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
