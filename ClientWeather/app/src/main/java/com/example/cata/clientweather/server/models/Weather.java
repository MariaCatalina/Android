package com.example.cata.clientweather.server.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cata on 12/10/2016.
 */

public class Weather {
    private int id;
    @SerializedName("main")
    private String weatherData;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(String weatherData) {
        this.weatherData = weatherData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", weatherData='" + weatherData + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
