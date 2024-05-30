package com.example.parkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkingapp.model.Reservation;
import com.example.parkingapp.retrofit.ReservationApi;
import com.example.parkingapp.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reservation);

        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText startTimeEditText = findViewById(R.id.startTimeEditText);
        TextInputEditText endTimeEditText = findViewById(R.id.endTimeEditText);
        TextInputEditText userIdEditText = findViewById(R.id.userIdEditText);
        TextInputEditText parkingSpotIdEditText = findViewById(R.id.parkingSpotIdEditText);
        MaterialButton saveButton = findViewById(R.id.saveReservationButton);

        RetrofitService retrofitService = new RetrofitService();
        ReservationApi reservationApi = retrofitService.getRetrofit().create(ReservationApi.class);

        saveButton.setOnClickListener(view -> {
            String startTime = String.valueOf(startTimeEditText.getText());
            String endTime = String.valueOf(endTimeEditText.getText());
            String userId = String.valueOf(userIdEditText.getText());
            String parkingSpotId = String.valueOf(parkingSpotIdEditText.getText());

            if (startTime.isEmpty() || endTime.isEmpty() || userId.isEmpty() || parkingSpotId.isEmpty()) {
                Toast.makeText(CreateReservationActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                Reservation reservation = new Reservation();
                reservation.setStartTime(startTime);
                reservation.setEndTime(endTime);
                reservation.setUserId(Long.parseLong(userId));
                reservation.setParkingSpotId(Long.parseLong(parkingSpotId));

                reservationApi.createReservation(reservation).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(CreateReservationActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(CreateReservationActivity.this, UserActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String errorMessage = "Save failed with error code: " + response.code();
                            Toast.makeText(CreateReservationActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(CreateReservationActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(CreateReservationActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                    }
                });
            } catch (NumberFormatException e) {
                Toast.makeText(CreateReservationActivity.this, "Invalid number format", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



