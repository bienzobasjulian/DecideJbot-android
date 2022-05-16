/*package com.example.decidejbot.controller;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.decidejbot.services.ListaParticipantesService;

import java.util.ArrayList;
import java.util.List;

public class ListaParticipantesController {

    private ListaParticipantesService listaParticipantesService = new ListaParticipantesService();

    //Función registrar lista
    public void registrarLista(ListaParticipantes lista, LocalDatabase localDb){

        listaParticipantesService.registrarLista(lista, localDb);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ListaParticipantes create(ArrayList<String> participantes) {

        ListaParticipantes lista = listaParticipantesService.create(participantes);
        return lista;
    }

    public List<ListaParticipantes> getAll(LocalDatabase localDb) {
        List<ListaParticipantes> lista = listaParticipantesService.getAll(localDb);
        return lista;
    }
}*/
