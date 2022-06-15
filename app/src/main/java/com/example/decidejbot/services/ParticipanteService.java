package com.example.decidejbot.services;

import androidx.annotation.NonNull;

import com.example.decidejbot.classes.Participante;
import com.example.decidejbot.classes.Sorteo;
import com.example.decidejbot.repository.ParticipanteRepository;

import java.util.ArrayList;
import java.util.List;

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

    public List<Sorteo> getAllGroupInSorteos() {

        List<Participante> participantes =  participanteRepository.getAll();
       List<Sorteo> listasParticipantes = groupInSorteos(participantes);

       return listasParticipantes;


    }

    public List<Sorteo> groupInSorteos(List<Participante> participantes){


        List<Sorteo> sorteos = new ArrayList<>();

        for (Participante participante : participantes){
            //Recorrer los participantes y obtener el sorteo en el que participan
            Sorteo sorteo = participante.getSorteo();

            System.out.println(sorteo.toString());
            if (!sorteos.contains(sorteo)){
                //Si la lista de sorteos no contiene el sorteo actual...

                sorteos.add(sorteo);


            }




        }

        System.out.println("Hay " + sorteos.size() + " sorteos");

        for (Sorteo sorteo : sorteos){
            List<Participante> participantesInSorteo = new ArrayList<>();

            for (Participante participante : participantes){

                if(participante.getSorteo().equals(sorteo)){

                    participantesInSorteo.add(participante);

                }
            }
            sorteo.setParticipantes(participantesInSorteo);

        }



        return sorteos;
    }


    public void deleteList(List<Participante> participantesOfSorteo) {

        for (int i = 0; i < participantesOfSorteo.size(); i++){
            participanteRepository.delete(participantesOfSorteo.get(i));
        }
    }
}
