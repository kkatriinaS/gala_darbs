package com.example.parkingapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.example.parkingapp.adapters.ParkingAreaAdapter;
import com.example.parkingapp.model.ParkingArea;
import com.example.parkingapp.retrofit.ParkingAreaApi;
import com.example.parkingapp.retrofit.RetrofitService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllParkingAreasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ParkingAreaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_parking_areas);

        recyclerView = findViewById(R.id.recyclerViewParkingAreas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RetrofitService retrofitService = new RetrofitService();
        ParkingAreaApi parkingAreaApi = retrofitService.getRetrofit().create(ParkingAreaApi.class);

        parkingAreaApi.selectAllParkingArea().enqueue(new Callback<List<ParkingArea>>() {
            @Override
            public void onResponse(Call<List<ParkingArea>> call, Response<List<ParkingArea>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new ParkingAreaAdapter(response.body());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(ShowAllParkingAreasActivity.this, "Failed to retrieve parking areas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ParkingArea>> call, Throwable t) {
                Toast.makeText(ShowAllParkingAreasActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

