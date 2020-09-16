package com.example.clearweather.presentation;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.clearweather.R;
import com.example.clearweather.data.network.Api;
import com.example.clearweather.data.network.RetrofitInit;
import com.example.clearweather.data.repository.WRepositoryImpl;
import com.example.clearweather.domain.repository.WRepository;
import com.example.clearweather.domain.usecase.Interactor;

public class MainActivity extends AppCompatActivity {

    private TextView placeTextView;
    private TextView tempTextView;
    private EditText editText;
    private Button okButton;

    private WViewModel wViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewModel();

        placeTextView = findViewById(R.id.place_view);
        tempTextView = findViewById(R.id.temp_view);
        editText = findViewById(R.id.place_input);
        okButton = findViewById(R.id.ok_button);

        okButton.setOnClickListener(view -> {
            wViewModel.getWeather(editText.getText().toString());
        });

        wViewModel.place.observe(this, data -> {
            placeTextView.setText(data);
        });
        wViewModel.temp.observe(this, data -> {
            tempTextView.setText(data);
        });


    }

    private void initViewModel() {
        Api api = RetrofitInit.newApiInstance();
        WRepository repository = new WRepositoryImpl(api);
        Interactor interactor = new Interactor(repository);

        wViewModel = new ViewModelProvider(this, new WViewModelFactory(interactor)).get(WViewModel.class);
        getLifecycle().addObserver(wViewModel);
    }

}