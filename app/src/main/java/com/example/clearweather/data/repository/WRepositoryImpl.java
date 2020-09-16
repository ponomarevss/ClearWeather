package com.example.clearweather.data.repository;

import com.example.clearweather.data.model.WeatherRequestRestModel;
import com.example.clearweather.data.network.Api;
import com.example.clearweather.domain.model.WeatherModel;
import com.example.clearweather.domain.repository.WRepository;

import java.io.IOException;

import retrofit2.Response;

public class WRepositoryImpl implements WRepository {

    private static final String MOSCOW = "Moscow";
    private static final String WEATHER_API_KEY = "024d1b99366c70a5b4bd878066cb6383";
    private static final String METRIC = "metric";

    private Api api;

    public WRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public WeatherModel getWeather(String query) {
        Response<WeatherRequestRestModel> response;
        try {
            response = api.getWeather(query, WEATHER_API_KEY, METRIC).execute();
        } catch (IOException e) {
            return null;
        }
        if (response.code() == 200 && response.body() != null) {
            return initWeatherModel(response.body());
        } else {
            return null;
        }
    }

    private WeatherModel initWeatherModel(WeatherRequestRestModel response) {
        WeatherModel weather = new WeatherModel();
        weather.setPlace(response.name);
        weather.setTemp(String.valueOf(response.main.temp));
        return weather;
    }
}
