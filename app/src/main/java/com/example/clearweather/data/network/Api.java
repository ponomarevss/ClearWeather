package com.example.clearweather.data.network;


import com.example.clearweather.data.model.WeatherRequestRestModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("data/2.5/weather")
    Call<WeatherRequestRestModel> getWeather(@Query("q") String name,
                                             @Query("appid") String apiKey,
                                             @Query("units") String units);
}
