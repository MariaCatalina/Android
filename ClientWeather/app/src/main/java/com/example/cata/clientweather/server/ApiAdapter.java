package com.example.cata.clientweather.server;

import com.example.cata.clientweather.server.responses.WeatherResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cata on 12/10/2016.
 */

public class ApiAdapter {
    private static ApiAdapter apiAdapter;
    private WeatherApi weatherApi;

    private final static String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    public static ApiAdapter getInstance(){
        if (apiAdapter == null)
            apiAdapter  = new ApiAdapter();

        return apiAdapter;
    }

    private ApiAdapter(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApi = retrofit.create(WeatherApi.class);
    }

    public void getWeatherData(String location, Callback<WeatherResponse> callback) {
        Call<WeatherResponse> call = weatherApi.getWeatherData(location);
        call.enqueue(callback);
    }
}
