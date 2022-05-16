package com.example.decidejbot.services;

import com.example.decidejbot.classes.Sorteo;
import com.example.decidejbot.repository.SorteoRepository;

public class SorteoService {
    private SorteoRepository sorteoRepository = new SorteoRepository();

    public Sorteo create(String titulo) {

        Sorteo nuevoSorteo = new Sorteo(titulo);
        return nuevoSorteo;
    }

    public void save(Sorteo nuevoSorteo) {

        sorteoRepository.save(nuevoSorteo);
    }
}
