package com.example.decidejbot.controller;

import com.example.decidejbot.classes.Sorteo;
import com.example.decidejbot.services.SorteoService;

import java.util.List;


public class SorteoController {
    private SorteoService sorteoService = new SorteoService();


    public Sorteo create(String titulo){


        Sorteo sorteo = sorteoService.create(titulo);

        return sorteo;

    }

    public List<Sorteo> getAll(){
       return sorteoService.getAll();
    }

    public void save(Sorteo nuevoSorteo) {

        sorteoService.save(nuevoSorteo);
    }


}
