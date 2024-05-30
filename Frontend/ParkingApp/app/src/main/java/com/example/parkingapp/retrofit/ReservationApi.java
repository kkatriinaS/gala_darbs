package com.example.parkingapp.retrofit;

import com.example.parkingapp.model.Reservation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReservationApi {
    @POST("/reservation/create")
    Call<Void> createReservation(@Body Reservation reservation);
}

