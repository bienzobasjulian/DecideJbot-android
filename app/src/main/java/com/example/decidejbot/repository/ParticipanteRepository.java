package com.example.decidejbot.repository;

import com.example.decidejbot.classes.Participante;

public class ParticipanteRepository {

    public void save(Participante participante) {
        participante.save();
    }
}
