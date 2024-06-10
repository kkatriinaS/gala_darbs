package com.example.parkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parkingapp.model.ParkingArea;
import com.example.parkingapp.retrofit.ParkingAreaApi;
import com.example.parkingapp.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    private Spinner modelSpinner;
    private Button selectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        modelSpinner = findViewById(R.id.modelSpinner);
        selectButton = findViewById(R.id.selectButton);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedModel = modelSpinner.getSelectedItem().toString();
                if (!selectedModel.isEmpty()) {
                    Intent intent = new Intent(UserActivity.this, ParkingAreaImageActivity.class);
                    intent.putExtra("selectedModel", selectedModel);
                    startActivity(intent);
                } else {
                    Toast.makeText(UserActivity.this, "Please select a parking area", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}



