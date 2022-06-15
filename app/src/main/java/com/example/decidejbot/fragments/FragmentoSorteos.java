package com.example.decidejbot.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.decidejbot.CaraCruz;
import com.example.decidejbot.activities.Dado;
import com.example.decidejbot.activities.Flecha;
import com.example.decidejbot.activities.NumerosAleatorios;
import com.example.decidejbot.R;
import com.example.decidejbot.activities.SorteoSimple;


public class FragmentoSorteos extends Fragment implements View.OnClickListener{
    CardView cardSorteoSimple, cardNumerosAleatorios, cardFlecha, cardDado, cardCoin;


    public FragmentoSorteos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragmento_sorteos, container, false);

        cardSorteoSimple = view.findViewById(R.id.cardSorteoSimple);
        cardNumerosAleatorios = view.findViewById(R.id.cardNumerosAleatorios);
        cardFlecha = view.findViewById(R.id.cardFlecha);
        cardDado = view.findViewById(R.id.cardDado);
        cardCoin = view.findViewById(R.id.cardCoin);


        cardSorteoSimple.setOnClickListener(this);
        cardNumerosAleatorios.setOnClickListener(this);
        cardFlecha.setOnClickListener(this);
        cardDado.setOnClickListener(this);
        cardCoin.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.cardSorteoSimple:
                 intent = new Intent(getActivity(), SorteoSimple.class);
                break;
            case R.id.cardNumerosAleatorios:
                 intent = new Intent(getActivity(), NumerosAleatorios.class);
                break;
            case R.id.cardFlecha:
                intent = new Intent(getActivity(), Flecha.class);
                break;
            case R.id.cardDado:
                intent = new Intent(getActivity(), Dado.class);
                break;
            case R.id.cardCoin:
                intent = new Intent(getActivity(), CaraCruz.class);
                break;



        }
        startActivity(intent);

    }
}