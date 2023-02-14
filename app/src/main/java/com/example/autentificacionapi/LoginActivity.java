package com.example.autentificacionapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button login;

    EditText username, password;

    TextView error;

    ProgressBar progressBar;

    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        error = findViewById(R.id.errorTextView);
        progressBar = findViewById(R.id.progressBar);

        // Recoger el ViewModel de esta actividad

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // Observar cuando el ViewModel tenga respuesta

        loginViewModel.getLoginResponse().observe(this , (loginResponse) -> {
            login.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            if (loginResponse.getNonFieldErrors().size() > 0) {
                error.setText(loginResponse.getNonFieldErrors().get(0));
            } else {
                // Lanzar siguiente actividad
            }

        })
        ;
        // Al hacer click en login hacer llamada de ViewModel

        login.setOnClickListener((v) -> {
            login.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            loginViewModel.hacerLogin(username.getText().toString(), password.getText().toString());
        });

    }
}