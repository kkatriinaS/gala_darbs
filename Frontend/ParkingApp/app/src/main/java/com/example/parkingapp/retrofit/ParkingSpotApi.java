package com.example.parkingapp.retrofit;

import com.example.parkingapp.model.ParkingSpot;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ParkingSpotApi {

    @POST("parkingSpot/create")
    Call<Void> createParkingSpot(@Body ParkingSpot parkingSpot);

    @DELETE("/parkingArea/delete/{id}")
    Call<Void> deleteParkingSpotById(@Path("id") Long id);
}

