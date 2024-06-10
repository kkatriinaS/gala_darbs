package com.example.parkingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

public class AdminActivity extends AppCompatActivity {

    private Spinner modelSpinner;
    private Button createButton;
    private Button updateButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        modelSpinner = findViewById(R.id.modelSpinner);
        createButton = findViewById(R.id.createButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        createButton.setOnClickListener(v -> handleCreate());
        updateButton.setOnClickListener(v -> handleUpdate());
        deleteButton.setOnClickListener(v -> handleDelete());
    }

    private void handleAction(String selectedModel, Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    private void handleCreate() {
        String selectedModel = modelSpinner.getSelectedItem().toString();
        switch (selectedModel) {
            case "ParkingArea":
                handleAction(selectedModel, CreateParkingAreaActivity.class);
                break;
            case "Reservation":
                handleAction(selectedModel, CreateReservationActivity.class);
                break;
            case "ParkingSpot":
                handleAction(selectedModel, CreateParkingSpotActivity.class);
                break;
            default:
                break;
        }
    }

    private void handleUpdate() {
        String selectedModel = modelSpinner.getSelectedItem().toString();
        switch (selectedModel) {
            case "ParkingArea":
                handleAction(selectedModel, SelectParkingAreaActivity.class);
                break;
            case "Reservation":
                handleAction(selectedModel, UpdateParkingSpotActivity.class);
                break;
            case "ParkingSpot":
                handleAction(selectedModel, UpdateReservationActivity.class);
                break;
            default:
                break;
        }
    }

    private void handleDelete() {
        String selectedModel = modelSpinner.getSelectedItem().toString();
        switch (selectedModel) {
            case "ParkingArea":
                handleAction(selectedModel, DeleteParkingAreaActivity.class);
                break;
            case "Reservation":
                handleAction(selectedModel, DeleteReservationActivity.class);
                break;
            case "ParkingSpot":
                handleAction(selectedModel, DeleteParkingSpotActivity.class);
                break;
            default:
                break;
        }
    }
}

