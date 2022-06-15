package com.example.decidejbot.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.decidejbot.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();








        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {


                    // Si hay conexión a Internet en este momento
                    if (user == null){
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                        finish();
                    }
                    else
                    {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();
                    }







            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 3000);




    }
}