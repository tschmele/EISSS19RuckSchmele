package com.eisss19.tschmele.testclient;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestService {

    @GET("/")
    Call<String> helloWorld();

}
