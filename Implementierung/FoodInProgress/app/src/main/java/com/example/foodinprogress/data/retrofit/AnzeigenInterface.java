package com.example.foodinprogress.data.retrofit;


import androidx.media.AudioAttributesCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AnzeigenInterface {

    /*
    @GET("retrofit/json_object.json/")
    Call<Anzeigenliste> getDisplayPosts();
    */

    @Headers("origin: NOT1nAI7mVv7v2bOM7oY")
    @GET("anzeige")
    Call<List<Example>> getDisplayPosts();

    @Headers("origin: NOT1nAI7mVv7v2bOM7oY")
    @GET("anzeige")
    Call<Example> getDisplayPost();

    @Headers({"origin: NOT1nAI7mVv7v2bOM7oY",
            "Conten-Type: application/json"})
    @POST("anzeige")
    Call<Data> postDisplayPost(@Body Data data);

}
