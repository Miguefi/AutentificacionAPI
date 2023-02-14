package com.example.autentificacionapi.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.autentificacionapi.api.QuestionRepository;
import com.example.autentificacionapi.data.Volume;

public class DetailViewModel extends AndroidViewModel {

    private QuestionRepository questionRepository;
    private LiveData<Volume> volumeLiveData;

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

        //public DetailViewModel(@NonNull Application application) {
        //    super(application);
        //}

        public void init() {
            questionRepository = new QuestionRepository();
            volumeLiveData = questionRepository.getVolumeLiveData();
        }

        public void searchVolumesById(String id) {
            //Dotenv dotenv = Dotenv.configure().directory("/assets").filename("env").load();
            //bookRepository.searchVolumes(keyword, author, dotenv.get("GOOGLE_API_KEY"));
            questionRepository.searchVolumesById(id);
        }

        public LiveData<Volume> getVolumeLiveData() {
            return volumeLiveData;
        }

}
