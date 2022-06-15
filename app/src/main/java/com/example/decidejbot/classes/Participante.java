package com.example.decidejbot.classes;

import com.orm.SugarRecord;

public class Participante extends SugarRecord<Participante> {

    String valor;
    Sorteo sorteo;


    public Participante() {
    }


    public Participante(String valor, Sorteo sorteo) {
        this.valor = valor;
        this.sorteo = sorteo;
    }


    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Sorteo getSorteo() {
        return this.sorteo;
    }

    public void setSorteo(Sorteo sorteo) {
        this.sorteo = sorteo;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "valor='" + valor + '\'' +
                ", sorteo=" + sorteo +
                '}';
    }
}