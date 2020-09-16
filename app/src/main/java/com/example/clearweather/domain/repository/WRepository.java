package com.example.clearweather.domain.repository;


import com.example.clearweather.domain.model.WeatherModel;

public interface WRepository {

    WeatherModel getWeather(String query);
}
