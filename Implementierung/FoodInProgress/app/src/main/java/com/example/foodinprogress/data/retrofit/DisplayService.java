package com.example.foodinprogress.data.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DisplayService {
/*
    @GET("/anzeige")
    Call<List<DisplayPost>> getDisplayPosts();

    @GET("/anzeige")
    Call<DisplayPost> getDisplayPost();
*/

    @GET("/")
    Call<String> getHW();

}
