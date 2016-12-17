package com.example.cata.clientweather.server.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Cata on 12/10/2016.
 */

public class WeatherData {
    private int dt;
    @SerializedName("main")
    private TempData tempData;

    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;

    @SerializedName("dt_txt")
    private String dataAndTime;

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public TempData getTempData() {
        return tempData;
    }

    public void setTempData(TempData tempData) {
        this.tempData = tempData;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getDataAndTime() {
        return dataAndTime;
    }

    public void setDataAndTime(String dataAndTime) {
        this.dataAndTime = dataAndTime;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "dt=" + dt +
                ", tempData=" + tempData +
                ", weather=" + weather +
                ", clouds=" + clouds +
                ", wind=" + wind +
                ", dataAndTime='" + dataAndTime + '\'' +
                '}';
    }
}
