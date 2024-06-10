package com.example.parkingapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parkingapp.model.ParkingArea;
import com.example.parkingapp.retrofit.ParkingAreaApi;
import com.example.parkingapp.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateParkingAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_parking_area);

        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditText = findViewById(R.id.formTextFieldName);
        TextInputEditText inputEditSpots = findViewById(R.id.formTextFieldSpots);
        MaterialButton buttonSave = findViewById(R.id.formButtonSave);

        RetrofitService retrofitService = new RetrofitService();
        ParkingAreaApi parkingAreaApi = retrofitService.getRetrofit().create(ParkingAreaApi.class);

        buttonSave.setOnClickListener(view -> {
            String areaName = String.valueOf(inputEditText.getText());
            String totalSpots = String.valueOf(inputEditSpots.getText());

            if (areaName.isEmpty() || totalSpots.isEmpty()) {
                Toast.makeText(CreateParkingAreaActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                ParkingArea parkingArea = new ParkingArea();
                parkingArea.setAreaName(areaName);
                parkingArea.setTotalSpots(Integer.parseInt(totalSpots));

                parkingAreaApi.createParkingArea(parkingArea).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(CreateParkingAreaActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(CreateParkingAreaActivity.this, AdminActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String errorMessage = "Save failed with error code: " + response.code();
                            Toast.makeText(CreateParkingAreaActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        Toast.makeText(CreateParkingAreaActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                    }
                });
            } catch (NumberFormatException e) {
                Toast.makeText(CreateParkingAreaActivity.this, "Invalid number format", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
