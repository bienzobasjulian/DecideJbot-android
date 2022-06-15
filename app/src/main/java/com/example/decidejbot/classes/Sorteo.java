package com.example.decidejbot.classes;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;
import java.util.Objects;

public class Sorteo extends SugarRecord<Sorteo> {

    String titulo;

    @Ignore
    List<Participante> participantes;


    public Sorteo(String titulo) {
        this.titulo = titulo;
    }



    public Sorteo() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    @Override
    public String toString() {
        return "Sorteo{" +
                "titulo='" + titulo + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sorteo sorteo = (Sorteo) o;
        return Objects.equals(id, sorteo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
