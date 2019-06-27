package com.example.foodinprogress.data.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderAPI {

    @GET("anzeigen")
    Call<List<DisplayPost>> getDisplayPosts();
}
