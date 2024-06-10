package com.example.parkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SelectParkingAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_parking_area);

        TextInputEditText parkingAreaIdEditText = findViewById(R.id.parkingAreaIdEditText);
        MaterialButton selectButton = findViewById(R.id.selectButton);

        selectButton.setOnClickListener(view -> {
            String idText = parkingAreaIdEditText.getText().toString();
            if (idText.isEmpty()) {
                Toast.makeText(SelectParkingAreaActivity.this, "ID field must be filled", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                Long id = Long.parseLong(idText);
                Intent intent = new Intent(SelectParkingAreaActivity.this, UpdateParkingAreaActivity.class);
                intent.putExtra("PARKING_AREA_ID", id);
                startActivity(intent);
                finish();
            } catch (NumberFormatException e) {
                Toast.makeText(SelectParkingAreaActivity.this, "Invalid ID format", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

