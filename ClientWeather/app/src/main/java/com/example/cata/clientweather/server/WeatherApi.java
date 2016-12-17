package com.example.cata.clientweather.server;

import com.example.cata.clientweather.server.responses.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Cata on 12/10/2016.
 */

public interface WeatherApi {
    String API_KEY = "1b0c10e2a4612bc6595754792eb47224";

    @GET("forecast?appid=" + API_KEY)
    Call<WeatherResponse> getWeatherData(@Query("q") String q);
}
