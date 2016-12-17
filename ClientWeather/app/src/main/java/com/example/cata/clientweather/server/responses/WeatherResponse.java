package com.example.cata.clientweather.server.responses;

import com.example.cata.clientweather.server.models.City;
import com.example.cata.clientweather.server.models.WeatherData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Cata on 12/10/2016.
 */

public class WeatherResponse {

    private City city;
    private int cod;
    private float message;
    private int cnt;

    @SerializedName("list")
    private List<WeatherData> weatherData;


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(float message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherData> getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(List<WeatherData> weatherData) {
        this.weatherData = weatherData;
    }

    @Override
        public String toString() {
            return "WeatherResponse{" +
                    "city=" + city +
                    ", cod=" + cod +
                    ", message=" + message +
                    ", cnt=" + cnt +
                    ", weatherData=" + weatherData +
                    '}';
    }
}
