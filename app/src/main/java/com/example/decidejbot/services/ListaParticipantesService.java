/*
package com.example.decidejbot.services;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.decidejbot.factory.ListaParticipantesFactory;
import com.example.decidejbot.repository.ListaParticipantesRepository;

import java.util.ArrayList;
import java.util.List;

public class ListaParticipantesService {

    ListaParticipantesFactory listaParticipantesFactory = new ListaParticipantesFactory();

    public void registrarLista(ListaParticipantes lista, LocalDatabase localDb) {

        ListaParticipantesDAO dao = localDb.listaParticipantesDao();

        ListaParticipantesRepository listaParticipantesRepository = new ListaParticipantesRepository(dao);

        listaParticipantesRepository.insertLista(lista);



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ListaParticipantes create(ArrayList<String> participantes) {

        //Convertir la lista de participantes en una cadena
        String participantesS = listaParticipantesFactory.ArrayListOfStringsToString(participantes);

        ListaParticipantes lista = new ListaParticipantes(participantesS);

        return lista;


    }

    public  List<ListaParticipantes> getAll(LocalDatabase localDb) {

        ListaParticipantesDAO dao = localDb.listaParticipantesDao();

        ListaParticipantesRepository listaParticipantesRepository = new ListaParticipantesRepository(dao);

        List<ListaParticipantes> lista = listaParticipantesRepository.getAll();

        return lista;


    }
}
*/
