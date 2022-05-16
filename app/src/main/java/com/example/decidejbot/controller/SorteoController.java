package com.example.decidejbot.controller;

import com.example.decidejbot.classes.Sorteo;
import com.example.decidejbot.services.SorteoService;


public class SorteoController {
    private SorteoService sorteoService = new SorteoService();


    public Sorteo create(){
        //TODO: Recibir título/descripción del sorteo
        String titulo = "Sorteo simple de prueba";

        Sorteo sorteo = sorteoService.create(titulo);

        return sorteo;

    }

    public void save(Sorteo nuevoSorteo) {

        sorteoService.save(nuevoSorteo);
    }
}
