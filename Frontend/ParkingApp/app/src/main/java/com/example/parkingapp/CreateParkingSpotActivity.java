package com.example.parkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkingapp.model.ParkingSpot;
import com.example.parkingapp.model.SpotStatus;
import com.example.parkingapp.retrofit.ParkingSpotApi;
import com.example.parkingapp.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateParkingSpotActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_parking_spot);

        initializeComponents();
    }

    private void initializeComponents() {
        Spinner spotStatusSpinner = findViewById(R.id.spotStatusSpinner);
        Button saveButton = findViewById(R.id.saveButton);


        ArrayAdapter<SpotStatus> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SpotStatus.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spotStatusSpinner.setAdapter(adapter);

        RetrofitService retrofitService = new RetrofitService();
        ParkingSpotApi parkingSpotApi = retrofitService.getRetrofit().create(ParkingSpotApi.class);

        saveButton.setOnClickListener(view -> {
            SpotStatus selectedStatus = (SpotStatus) spotStatusSpinner.getSelectedItem();

            if (selectedStatus == null) {
                Toast.makeText(CreateParkingSpotActivity.this, "Spot status must be selected", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                ParkingSpot parkingSpot = new ParkingSpot();
                parkingSpot.setSpotStatus(String.valueOf(selectedStatus));

                parkingSpotApi.createParkingSpot(parkingSpot).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(CreateParkingSpotActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(CreateParkingSpotActivity.this, AdminActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String errorMessage = "Save failed with error code: " + response.code();
                            Toast.makeText(CreateParkingSpotActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(CreateParkingSpotActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(CreateParkingSpotActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                    }
                });
            } catch (Exception e) {
                Toast.makeText(CreateParkingSpotActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }
}



