package com.example.autentificacionapi.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.autentificacionapi.api.QuestionRepository;

//import io.github.cdimascio.dotenv.Dotenv;

public class QuestionSearchViewModel extends AndroidViewModel {
    private QuestionRepository questionRepository;
    private LiveData<VolumesResponse> volumesResponseLiveData;

    public QuestionSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        questionRepository = new QuestionRepository();
        volumesResponseLiveData = questionRepository.getVolumesResponseLiveData();
    }

    public void searchVolumes(String keyword, String author, String startIndex) {
        //Dotenv dotenv = Dotenv.configure().directory("/assets").filename("env").load();
        //bookRepository.searchVolumes(keyword, author, dotenv.get("GOOGLE_API_KEY"));
        questionRepository.searchVolumes(keyword, author, startIndex);
    }

    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }
}
