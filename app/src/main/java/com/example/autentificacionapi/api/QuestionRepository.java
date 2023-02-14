package com.example.autentificacionapi.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.autentificacionapi.data.Volume;
import com.example.autentificacionapi.data.VolumesResponse;

//import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionRepository {

    private static final String QUESTION_SEARCH_SERVICE_BASE_URL = "192.168.20.127/";

    private QuestionSearchService questionSearchService;
    private MutableLiveData<VolumesResponse> volumesResponseLiveData;
    private MutableLiveData<Volume> volumeLiveData;

    public QuestionRepository() {
        volumesResponseLiveData = new MutableLiveData<>();
        volumeLiveData = new MutableLiveData<>();

        /*HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();*/

        questionSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(QUESTION_SEARCH_SERVICE_BASE_URL)
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(QuestionSearchService.class);

    }

    public void searchVolumes(String keyword, String author) {
        searchVolumes(keyword, author, "0");
    }

    public void searchVolumes(String keyword, String author, String startIndex) {
        questionSearchService.searchVolumes(keyword, author, startIndex)
                .enqueue(new Callback<VolumesResponse>() {
                    @Override
                    public void onResponse(Call<VolumesResponse> call, Response<VolumesResponse> response) {
                        if (response.body() != null) {
                            volumesResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<VolumesResponse> call, Throwable t) {
                        volumesResponseLiveData.postValue(null);
                    }
                });
    }

    public void searchVolumesById(String id) {
        questionSearchService.searchVolumesById(id)
                .enqueue(new Callback<Volume>() {
                    @Override
                    public void onResponse(Call<Volume> call, Response<Volume> response) {
                        if (response.body() != null) {
                            volumeLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Volume> call, Throwable t) {
                        volumeLiveData.postValue(null);
                    }
                });
    }

    /*public void searchVolumes(String keyword, String author, String apiKey) {
        bookSearchService.searchVolumes(keyword, author, apiKey)
                .enqueue(new Callback<VolumesResponse>() {
                    @Override
                    public void onResponse(Call<VolumesResponse> call, Response<VolumesResponse> response) {
                        if (response.body() != null) {
                            volumesResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<VolumesResponse> call, Throwable t) {
                        volumesResponseLiveData.postValue(null);
                    }
                });
    }*/

    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }

    public LiveData<Volume> getVolumeLiveData() {
        return volumeLiveData;
    }
}
