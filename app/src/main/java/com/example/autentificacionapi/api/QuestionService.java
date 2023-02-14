package com.example.autentificacionapi.api;

import com.example.autentificacionapi.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface QuestionService {
    @POST("/polls/api-token-auth/")
    Call<LoginResponse> login(@Body LoginCredential credential);

    //@GET("/polls/api/question/")
}
