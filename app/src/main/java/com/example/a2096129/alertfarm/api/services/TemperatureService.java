package com.example.a2096129.alertfarm.api.services;


import com.example.a2096129.alertfarm.api.alertFarmException.ServicesException;
import com.example.a2096129.alertfarm.entities.Temperature;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TemperatureService {

    @GET("api/temperature/{id}")
    Call<List<Temperature>> getTemperatureByUser(@Path("id") int id);

    @GET("api/temperature")
    Call<List<Temperature>> getTemperatureByUser();
}
