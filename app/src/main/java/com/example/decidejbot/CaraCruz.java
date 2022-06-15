package com.example.decidejbot;

import androidx.appcompat.app.AppCompatActivity;
import com.example.decidejbot.R;
import com.google.android.material.button.MaterialButton;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class CaraCruz extends AppCompatActivity {

    Random random = new Random();

    ImageView imgCoin;
    MaterialButton btnLanzarCoin;
    TextView txtValorCoin;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cara_cruz);

        imgCoin = findViewById(R.id.imgMoneda);
        btnLanzarCoin = findViewById(R.id.btnLanzarMoneda);
        txtValorCoin = findViewById(R.id.txtValorMoneda);
        mediaPlayer = MediaPlayer.create(this, R.raw.coin);

        imgCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarMoneda();
            }
        });

        btnLanzarCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarMoneda();
            }
        });



    }

     private void lanzarMoneda(){

         if (mediaPlayer != null){
             mediaPlayer.start();
         }

        boolean animationDirection = random.nextBoolean();
        if(animationDirection){
            imgCoin.animate().rotationYBy(1800f).setDuration(1000);
        }
        else{
            imgCoin.animate().rotationXBy(1800f).setDuration(1000);
        }


       boolean valorMoneda = random.nextBoolean();

       if (valorMoneda){
           //True equivale a cara
           imgCoin.setImageResource(R.drawable.face);
           txtValorCoin.setText("Cara");
       }
       else{
           imgCoin.setImageResource(R.drawable.cruz);
           txtValorCoin.setText("Cruz");
       }
     }
}