package com.example.foodinprogress.dispalydata;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static final String ROOT_URL = "https://192.168.43.204:2000/";

    /**
     * Get Retrofit Instance
     */


    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    /**
     * GET API Service
     *
     * @return API Service
     */
    public static AnzeigenInterface getAnzeigenService() {
        return getRetrofitInstance().create(AnzeigenInterface.class);
    }

}
