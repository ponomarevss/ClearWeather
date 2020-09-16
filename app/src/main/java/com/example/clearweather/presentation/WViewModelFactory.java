package com.example.clearweather.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.clearweather.domain.usecase.Interactor;

public class WViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Interactor interactor;

    public WViewModelFactory(Interactor interactor) {
        this.interactor = interactor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == WViewModel.class) {
            return (T) new WViewModel(interactor);
        }
        return null;
    }
}
