package com.example.decidejbot.repository;

import com.example.decidejbot.classes.Participante;
import com.example.decidejbot.classes.Sorteo;

import java.util.List;

public class ParticipanteRepository {

    public void save(Participante participante) {
        participante.save();
    }

    public List<Participante> getAll() {
        //Obtenemos todos los participantes
        List<Participante> participantes = Participante.listAll(Participante.class);




        return participantes;
    }


    public void delete(Participante participante) {
        participante.delete();
    }
}
