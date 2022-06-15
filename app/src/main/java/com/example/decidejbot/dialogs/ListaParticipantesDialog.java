
package com.example.decidejbot.dialogs;


import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.decidejbot.R;
import com.example.decidejbot.adapters.ParticipantesAdapter;
import com.example.decidejbot.classes.Sorteo;

import java.util.List;

public class ListaParticipantesDialog {

    Activity activity;
    AlertDialog dialog;
    List<Sorteo> sorteos;


    public ListaParticipantesDialog(Activity activity, List<Sorteo> sorteos) {
        this.activity = activity;
        this.sorteos = sorteos;
    }

    public void mostrarListas(List<Sorteo> sorteos){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_listas_participantes,null);

        ListView listView = (ListView) view.findViewById(R.id.listaListasParticipantesLocales);


        builder.setView(view);

        dialog = builder.create();

        ParticipantesAdapter adapter = new ParticipantesAdapter(activity, sorteos, activity.getApplicationContext(), dialog);
        listView.setAdapter(adapter);

        dialog.show();



    }


}

