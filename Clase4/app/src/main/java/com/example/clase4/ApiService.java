package com.example.clase4;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("start")
    Call<StartResponse> startSession(@Body StartRequest request);

    @POST("choice")
    Call<ChoiceResponse> sendChoice(@Body ChoiceRequest request);
}