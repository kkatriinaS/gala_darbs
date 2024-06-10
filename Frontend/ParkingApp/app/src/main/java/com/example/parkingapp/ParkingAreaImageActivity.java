package com.example.parkingapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ParkingAreaImageActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_area_image);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        // Get the selected parking area data from the intent
        String selectedParkingArea = getIntent().getStringExtra("selectedParkingArea");

        // Set the text view to display the selected parking area
        textView.setText("Selected Parking Area: " + selectedParkingArea);

        // TODO: Generate/display the image corresponding to the selected parking area
        // You can load an image from resources or download it from a server based on the selected parking area
        // For example:
        // imageView.setImageResource(R.drawable.parking_area_image);
        // Or you can use a library like Picasso or Glide to load an image from a URL
    }
}
