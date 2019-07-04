package com.example.foodinprogress.dispalydata;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AnzeigenInterface {

    @GET("retrofit/json_object.json")
    Call<Anzeigenliste> getAnzeigenJSON();

}
