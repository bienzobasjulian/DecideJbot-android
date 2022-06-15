package com.example.decidejbot.adapters;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.decidejbot.R;

import com.example.decidejbot.activities.SorteoSimple;
import com.example.decidejbot.classes.Participante;
import com.example.decidejbot.classes.Sorteo;
import com.example.decidejbot.dialogs.ListaParticipantesDialog;
import com.example.decidejbot.services.ParticipanteService;
import com.example.decidejbot.services.SorteoService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParticipantesAdapter extends BaseAdapter {

    private List<Sorteo> sorteos;
    private Context context;
    Activity activity;
    AlertDialog dialog;


    public ParticipantesAdapter(Activity myActivity, List<Sorteo> sorteos, Context context, AlertDialog dialog) {
        this.activity = myActivity;
        this.sorteos = sorteos;
        this.context = context;
        this.dialog = dialog;

    }

    @Override
    public int getCount() {
        return sorteos.size();
    }

    @Override
    public Object getItem(int position) {
        return sorteos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(context);
        View elemento = mostrado.inflate(R.layout.listitem_participante, null, false);
        TextView textoDescripcion = (TextView) elemento.findViewById(R.id.descripcionParticipantes);
        TextView textoParticipantes = (TextView) elemento.findViewById(R.id.participantes);

        Button btnAddParticipantes = (Button) elemento.findViewById(R.id.btnAddParticipantes);
        Button btnReplaceParticipantes = (Button) elemento.findViewById(R.id.btnReemplazarParticipantes);
        Button btnEliminarParticipantes = (Button) elemento.findViewById(R.id.btnEliminarParticipantes);




        textoDescripcion.setText(sorteos.get(position).getTitulo());

        sorteos.get(position).getParticipantes().size();
        String participantes = "";

        for (int i = 0; i < sorteos.get(position).getParticipantes().size(); i++){
            participantes +=  sorteos.get(position).getParticipantes().get(i).getValor() + " | ";
        }

        String participantesS = participantes;


        textoParticipantes.setText(participantesS);

        btnAddParticipantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (activity instanceof SorteoSimple){
                    Sorteo sorteoSeleccionado = sorteos.get(position);
                    ((SorteoSimple) activity).addPartipantesSeleccionados(sorteoSeleccionado);
                    dialog.dismiss();


                }
            }
        });

        btnReplaceParticipantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (activity instanceof  SorteoSimple){
                    Sorteo sorteoSeleccionado = sorteos.get(position);
                    ((SorteoSimple) activity).replaceParticipantes(sorteoSeleccionado);
                    dialog.dismiss();
                }

            }
        });

        btnEliminarParticipantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SorteoService sorteoService = new SorteoService();

                Sorteo sorteoSeleccionado = sorteos.get(position);

                sorteoService.delete(sorteoSeleccionado);

                dialog.dismiss();


            }
        });






        return elemento;
    }
}
