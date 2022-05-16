package com.example.decidejbot.services;

import com.example.decidejbot.classes.Participante;
import com.example.decidejbot.classes.Sorteo;
import com.example.decidejbot.repository.ParticipanteRepository;

import java.util.ArrayList;

public class ParticipanteService {
    ParticipanteRepository participanteRepository = new ParticipanteRepository();
    public ArrayList<Participante> createList(ArrayList<String> participantes, Sorteo nuevoSorteo) {

        ArrayList<Participante> listaDeParticipantes = new ArrayList<>();

        for (int i = 0; i < participantes.size(); i++){
            Participante nuevoParticipante = new Participante(participantes.get(i), nuevoSorteo);
            listaDeParticipantes.add(nuevoParticipante);
        }

        return listaDeParticipantes;
    }

    public void save(ArrayList<Participante> listaDeParticipantes) {
        //Recorrer y almacenar los participantes

        for (int i = 0; i < listaDeParticipantes.size(); i++){
            Participante participante = listaDeParticipantes.get(i);

            participanteRepository.save(participante);

        }
    }
}
