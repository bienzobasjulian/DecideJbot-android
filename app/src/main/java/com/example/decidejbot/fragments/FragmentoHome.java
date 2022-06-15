package com.example.decidejbot.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.decidejbot.R;
import com.example.decidejbot.activities.LoginActivity;
import com.example.decidejbot.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FragmentoHome extends Fragment implements View.OnClickListener {

    CardView cardSesion, cardWeb;
    TextView titleCardSesion;
    ImageView imgCardSesion;
    private FirebaseAuth firebaseAuth;
    FirebaseUser user;



    public FragmentoHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_home, container, false);

        cardSesion = view.findViewById(R.id.cardSesion);
        cardWeb = view.findViewById(R.id.cardWeb);
        titleCardSesion = (TextView) view.findViewById(R.id.titleCardSesion);

        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser();



        if (user == null){
                titleCardSesion.setText("Iniciar sesión");
        }
        else{
            titleCardSesion.setText("Cerrar  sesión");
        }






        cardSesion.setOnClickListener(this);
        cardWeb.setOnClickListener(this);




        return view;
    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.cardSesion:
                if (user == null){

                        intent = new Intent(getActivity(), LoginActivity.class);

                }
                else{
                   firebaseAuth.signOut();
                    intent = new Intent(getActivity(), MainActivity.class);
                }

                break;

            case R.id.cardWeb:
                Uri uri = Uri.parse("https://bienzobasjulian.github.io/DecideJbot-WEB-Angular-/#/");
                intent = new Intent(Intent.ACTION_VIEW, uri);




                break;


        }
        startActivity(intent);

    }


}