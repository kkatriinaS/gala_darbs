package com.example.parkingapp.retrofit;

import com.example.parkingapp.model.ParkingArea;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ParkingAreaApi {
    @GET("/parkingArea/showAll")
    Call<List<ParkingArea>> selectAllParkingArea();

    @POST("/parkingArea")
    Call<ParkingArea> save(@Body ParkingArea parkingArea);

    @POST("/parkingArea/create")
    Call<ParkingArea> createParkingArea(@Body ParkingArea parkingArea);
}

