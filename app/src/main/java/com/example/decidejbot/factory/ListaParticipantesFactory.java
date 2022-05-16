package com.example.decidejbot.factory;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaParticipantesFactory {

    //Convertir un ArrayList de Strings en un único string
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String ArrayListOfStringsToString(ArrayList<String> lista){

        String participantesS = String.join(", ", lista);

        return participantesS;
    }

    public List<String> StringToListOfStrings(String listaS){

        String string[] = listaS.split(",");
        List<String> lista = new ArrayList<String>();
        lista = Arrays.asList(string);


        return lista;
    }
}
