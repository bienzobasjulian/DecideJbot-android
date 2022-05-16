package com.example.decidejbot.classes;

import com.orm.SugarRecord;

public class Sorteo extends SugarRecord<Sorteo> {

    String titulo;

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

    @Override
    public String toString() {
        return "Sorteo{" +
                "titulo='" + titulo + '\'' +
                '}';
    }
}
