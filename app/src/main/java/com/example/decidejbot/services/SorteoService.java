package com.example.decidejbot.services;

import com.example.decidejbot.classes.Participante;
import com.example.decidejbot.classes.Sorteo;
import com.example.decidejbot.repository.SorteoRepository;

import java.util.List;

public class SorteoService {
    private SorteoRepository sorteoRepository = new SorteoRepository();
    private ParticipanteService participanteService = new ParticipanteService();

    public Sorteo create(String titulo) {

        Sorteo nuevoSorteo = new Sorteo(titulo);
        return nuevoSorteo;
    }

    public void save(Sorteo nuevoSorteo) {

        sorteoRepository.save(nuevoSorteo);
    }

    public List<Sorteo> getAll(){
        return participanteService.getAllGroupInSorteos();
    }

    public void delete(Sorteo sorteoSeleccionado) {

        List<Participante> participantesOfSorteo = sorteoSeleccionado.getParticipantes();

        participanteService.deleteList(participantesOfSorteo);

        sorteoRepository.delete(sorteoSeleccionado);
        }


}

