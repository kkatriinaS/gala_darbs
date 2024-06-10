package com.example.parkingapp.retrofit;

import com.example.parkingapp.model.ParkingArea;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ParkingAreaApi {

        @GET("/parkingArea/showAll")
        Call<List<ParkingArea>> selectAllParkingArea();

        @POST("/parkingArea/create")
        Call<Void> createParkingArea(@Body ParkingArea parkingArea);

        @DELETE("/parkingArea/delete/{id}")
        Call<Void> deleteParkingAreaById(@Path("id") Long id);

        @GET("api/parkingArea/{id}")
        Call<ParkingArea> getParkingAreaById(@Path("id") Long id);

        @PUT("api/parkingArea/{id}")
        Call<Void> updateParkingArea(@Path("id") Long id, @Body ParkingArea parkingArea);

}



