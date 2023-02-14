package com.example.autentificacionapi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.autentificacionapi.api.QuestionRepository;

import java.util.ArrayList;

public class LoginViewModel extends ViewModel {

    private static final double TEST_WAIT = 2000;
    private static final double SUCESS_PROBABILITY = 50;
    private MutableLiveData<LoginResponse> mlData;
    private QuestionRepository questionRepository;

    public LoginViewModel() {
        mlData = new MutableLiveData<LoginResponse>();
    }

    public LiveData<LoginResponse> getLoginResponse() {
        return mlData;
    }

    public void hacerLogin(String username , String password) {
        new Thread(() -> {
            try {
                Thread.sleep((long) (Math.random()*TEST_WAIT));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LoginResponse response = new LoginResponse();
            if (Math.random()>SUCESS_PROBABILITY) {
                response.setToken("LOHASCONSEGUIDOCAMPEON");
                response.setNonFieldErrors(new ArrayList<String>());
            } else {
                response.setToken("");
                response.setNonFieldErrors(new ArrayList<String>());
                response.getNonFieldErrors().add("Ha ocurrido un error");
            }
            mlData.postValue(response);
        }).start();
    }
}
