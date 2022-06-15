package com.example.decidejbot.controller;

import com.example.decidejbot.classes.Participante;
import com.example.decidejbot.classes.Sorteo;
import com.example.decidejbot.services.ParticipanteService;

import java.util.ArrayList;
import java.util.List;

public class ParticipanteController {
    private ParticipanteService participanteService = new ParticipanteService();

    public ArrayList<Participante> createList(ArrayList<String> participantes, Sorteo nuevoSorteo) {
        ArrayList<Participante>  listaDeParticipantes = participanteService.createList(participantes, nuevoSorteo);
        return listaDeParticipantes;
    }

    public void save(ArrayList<Participante> listaDeParticipantes) {
        participanteService.save(listaDeParticipantes);
    }

    public List<Sorteo> getAllGroupInSorteos() {
        List<Sorteo> listas =  participanteService.getAllGroupInSorteos();
        return  listas;
    }
}
