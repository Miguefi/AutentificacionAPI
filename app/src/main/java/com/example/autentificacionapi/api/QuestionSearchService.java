package com.example.autentificacionapi.api;

import com.example.autentificacionapi.data.VolumesResponse;
import com.example.autentificacionapi.data.Volume;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuestionSearchService {
    @GET("/polls/api-token-auth")
    Call<VolumesResponse> searchVolumes(
            @Query("q") String query,
            @Query("inauthor") String author,
            @Query("startIndex") String startIndex
            //,@Query("key") String apiKey
            );

    @POST("/books/v1/volumes/{volumeId}")
    Call<Volume> searchVolumesById(
            @Path("volumeId") String volumeId
    );

}