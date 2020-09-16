package com.example.clearweather.presentation;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.example.clearweather.domain.model.WeatherModel;
import com.example.clearweather.domain.usecase.Interactor;

public class WViewModel extends ViewModel implements LifecycleObserver {

    private Interactor interactor;

    public MutableLiveData<String> place = new MutableLiveData<>();
    public MutableLiveData<String> temp = new MutableLiveData<>();

    public WViewModel(Interactor interactor) {
        this.interactor = interactor;
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    public void onStart() {
//        new Thread() {
//            @Override
//            public void run() {
//                WeatherModel weather = interactor.getWeather();
//                place.postValue(weather.getPlace());
//                temp.postValue(weather.getTemp());
//            }
//        }.start();
//    }

    public void getWeather(String query) {
        new Thread(() -> {
            WeatherModel weather = interactor.getWeather(query);
            place.postValue(weather.getPlace());
            temp.postValue(weather.getTemp());
        }).start();
    }
}
