package com.example.autentificacionapi.api;

import retrofit2.converter.gson.GsonConverterFactory;


public class QuestionRepositorio {

    private static final String API_URL = "http://192.168.20.127:8000";

    private QuestionRepositorio instancia;

    private QuestionService service;

    private QuestionRepositorio(){
        service = new retrofit2.Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(QuestionService.class);
    }

    public QuestionRepositorio getInstance() {
        if (instancia == null) {
            instancia = new QuestionRepositorio();
        }
        return instancia;
    }

}
