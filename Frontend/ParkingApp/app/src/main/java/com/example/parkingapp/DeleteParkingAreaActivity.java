package com.example.parkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parkingapp.retrofit.ParkingAreaApi;
import com.example.parkingapp.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteParkingAreaActivity extends AppCompatActivity {
    private static final String TAG = "DeleteParkingAreaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_parking_area);

        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditTextId = findViewById(R.id.formTextFieldId);
        MaterialButton buttonDelete = findViewById(R.id.formButtonDelete);

        RetrofitService retrofitService = new RetrofitService();
        ParkingAreaApi parkingAreaApi = retrofitService.getRetrofit().create(ParkingAreaApi.class);

        buttonDelete.setOnClickListener(view -> {
            String idText = String.valueOf(inputEditTextId.getText());

            if (idText.isEmpty()) {
                Toast.makeText(DeleteParkingAreaActivity.this, "ID field must be filled", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                Long id = Long.parseLong(idText);
                Log.d(TAG, "Attempting to delete ParkingArea with ID: " + id);

                parkingAreaApi.deleteParkingAreaById(id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        Log.d(TAG, "Response code: " + response.code());
                        if (response.isSuccessful()) {
                            Toast.makeText(DeleteParkingAreaActivity.this, "Delete successful!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(DeleteParkingAreaActivity.this, AdminActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String errorMessage = "Delete failed with error code: " + response.code();
                            Toast.makeText(DeleteParkingAreaActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                            Log.e(TAG, errorMessage);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        Toast.makeText(DeleteParkingAreaActivity.this, "Delete failed!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        Log.e(TAG, "Error occurred", t);
                    }
                });
            } catch (NumberFormatException e) {
                Toast.makeText(DeleteParkingAreaActivity.this, "Invalid ID format", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Invalid ID format", e);
            }
        });
    }
}


