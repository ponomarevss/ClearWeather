package com.example.clearweather.domain.usecase;

import com.example.clearweather.domain.model.WeatherModel;
import com.example.clearweather.domain.repository.WRepository;

public class Interactor {

    private WRepository repository;

    public Interactor(WRepository repository) {
        this.repository = repository;
    }

    public WeatherModel getWeather(String query) {
        WeatherModel weather = repository.getWeather(query);
        if (weather == null) {
        return new WeatherModel();
        }
        return weather;
    }
}
