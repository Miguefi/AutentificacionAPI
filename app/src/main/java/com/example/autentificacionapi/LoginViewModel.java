package com.example.autentificacionapi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.autentificacionapi.api.QuestionRepository;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginResponse> mlData;
    private QuestionRepository questionRepository;

    public LoginViewModel() {
        mlData = new MutableLiveData<LoginResponse>();
    }

    public LiveData<LoginResponse> getLoginResponse() {
        return mlData;
    }

    public void hacerLogin(String username , String password) {

    }
}
