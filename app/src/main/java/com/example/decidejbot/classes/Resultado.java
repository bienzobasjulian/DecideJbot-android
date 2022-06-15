package com.example.decidejbot.classes;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.Date;

public class Resultado extends SugarRecord<Resultado> {

    @Ignore
    String id;

    Date fecha;

    Sorteo sorteo;

    //To Do : Ganadores


    public Resultado() {
    }

    public Resultado(Date fecha, Sorteo sorteo) {
        this.fecha = fecha;
        this.sorteo = sorteo;
    }


    public String getUid() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Sorteo getSorteo() {
        return sorteo;
    }

    public void setSorteo(Sorteo sorteo) {
        this.sorteo = sorteo;
    }
}
