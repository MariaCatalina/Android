package com.example.cata.clientweather.weatherapp.adaptors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cata.clientweather.R;
import com.example.cata.clientweather.weatherapp.models.DayWeatherData;

import java.util.List;

/**
 * Created by Cata on 12/10/2016.
 */

public class DayWeatherAdaptor extends RecyclerView.Adapter<DayWeatherAdaptor.Holder> {

    private List<DayWeatherData> weatherData;

    public DayWeatherAdaptor(List<DayWeatherData> data){
        super();
        this.weatherData = data;
    }

    public static class Holder extends RecyclerView.ViewHolder{
        private TextView hour;
        private ImageView image;
        private TextView grades;

        public Holder(View view) {
            super(view);

            this.hour = (TextView) view.findViewById(R.id.day_hour);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.grades = (TextView) view.findViewById(R.id.grades);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_weather,parent,false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.hour.setText(weatherData.get(position).getHour());
        holder.grades.setText(weatherData.get(position).getGrades());

        boolean setImage = false;
        if (weatherData.get(position).getDescription().contains("Clouds")) {
            holder.image.setImageResource(R.drawable.clouds_24);
            setImage = true;
        }

        if (weatherData.get(position).getDescription().contains("Rain")) {
            setImage = true;
            holder.image.setImageResource(R.drawable.rain_24);
        }

        if (weatherData.get(position).getDescription().contains("Clear")) {
            setImage = true;
            holder.image.setImageResource(R.drawable.sun_24);
        }

        if (!setImage)
            holder.image.setImageResource(R.drawable.bright_moon_24);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return weatherData.size();
    }
}
