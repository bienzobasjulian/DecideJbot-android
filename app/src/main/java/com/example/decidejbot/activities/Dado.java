package com.example.decidejbot.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.decidejbot.R;
import com.google.android.material.button.MaterialButton;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Dado extends AppCompatActivity {



    Random random = new Random();

    ImageView imgDado;
    MaterialButton btnLanzarDado;
    TextView txtValorDado;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dado);

        imgDado = findViewById(R.id.imgDado1);
        btnLanzarDado = findViewById(R.id.btnLanzarDado);
        txtValorDado = findViewById(R.id.txtValorDado);
        mediaPlayer = MediaPlayer.create(this, R.raw.roll_dice);

        btnLanzarDado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarDado();
                Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                imgDado.startAnimation(rotate);
            }
        });

        imgDado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    lanzarDado();
                    Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                    imgDado.startAnimation(rotate);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void lanzarDado(){

        if (mediaPlayer != null){
            mediaPlayer.start();
        }
      int valorDado = random.nextInt(11);
      String valorDadoS = Integer.toString(valorDado);


      switch (valorDado){
          case 0:
              imgDado.setImageResource(R.drawable.dice0);
              break;
          case 1:
              imgDado.setImageResource(R.drawable.dice1);
              break;
          case 2:
              imgDado.setImageResource(R.drawable.dice2);
              break;
          case 3:
              imgDado.setImageResource(R.drawable.dice3);
              break;
          case 4:
              imgDado.setImageResource(R.drawable.dice4);
              break;
          case 5:
              imgDado.setImageResource(R.drawable.dice5);
              break;
          case 6:
              imgDado.setImageResource(R.drawable.dice6);
              break;
          case 7:
              imgDado.setImageResource(R.drawable.dice7);
              break;
          case 8:
              imgDado.setImageResource(R.drawable.dice8);
              break;
          case 9:
              imgDado.setImageResource(R.drawable.dice9);
              break;
          case 10:
              imgDado.setImageResource(R.drawable.dice10);
              break;

      }

      if (valorDado == 0){
            txtValorDado.setText("¡Tú decides!");
      }
      else {
          txtValorDado.setText(valorDadoS);
      }


    }
}