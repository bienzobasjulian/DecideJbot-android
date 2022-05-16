package com.example.decidejbot.repository;

import com.example.decidejbot.classes.Sorteo;

public class SorteoRepository
{
    public void save(Sorteo nuevoSorteo) {

        nuevoSorteo.save();
    }
}
