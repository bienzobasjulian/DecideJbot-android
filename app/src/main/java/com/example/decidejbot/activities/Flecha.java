package com.example.decidejbot.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.decidejbot.R;
import com.google.android.material.button.MaterialButton;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class Flecha extends AppCompatActivity {

    ImageView imgFlecha;
    MaterialButton btnGirarFlecha;
    Random randomNumber = new Random();
    private int ultimaDireccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flecha);

        imgFlecha = (ImageView) findViewById(R.id.imgFlecha);
        btnGirarFlecha = (MaterialButton) findViewById(R.id.btnGirarFlecha);


        imgFlecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girarFlecha();
            }
        });

        btnGirarFlecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girarFlecha();
            }
        });





    }

    private void girarFlecha(){

        int nuevaDireccion = randomNumber.nextInt(3600) + 360;
        float pivoitX = (float) imgFlecha.getWidth() / 2;
        float pivoitY = (float) imgFlecha.getHeight() / 2;

        Animation rotate = new RotateAnimation(ultimaDireccion, nuevaDireccion, pivoitX, pivoitY);
        rotate.setDuration(2500);
        rotate.setFillAfter(true);

        ultimaDireccion = nuevaDireccion;

        imgFlecha.startAnimation(rotate);

    }

}