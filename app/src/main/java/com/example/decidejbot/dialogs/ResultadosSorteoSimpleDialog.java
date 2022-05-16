package com.example.decidejbot.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.decidejbot.R;

import java.util.ArrayList;
import java.util.List;

public class ResultadosSorteoSimpleDialog {

    Activity activity;
    AlertDialog dialog;

    ArrayList<String> participantes;
    ArrayList<String> ganadores;
    int numPremios;
    ListView listView;

    public ResultadosSorteoSimpleDialog(Activity myActivity, ArrayList<String> participantes, int numPremios){
        this.activity = myActivity;
        this.participantes = participantes;
        this.numPremios = numPremios;
    }

    public void mostrarResultados(){
        //1º Establecemos los ganadores
        setGanadores();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_resultado_sorteo_simple,null);

        listView = (ListView) view.findViewById(R.id.listaGanadoresSorteoSimple);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity.getApplicationContext(),
                android.R.layout.simple_list_item_1, ganadores);
        listView.setAdapter(adapter);


        builder.setView(view);

        dialog = builder.create();
        dialog.show();


    }

    public void setGanadores(){
        ganadores= new ArrayList<>();
        for (int i = 0; i < numPremios; i++)
        {
            //Escoger un ganador aleatorio en la lista de participantes
            int randomIndex = (int)(Math.random() * participantes.size());
            String ganador = participantes.get(randomIndex);

            if (ganadores.contains(ganador))
            {
                i--;
            }
            else
            {
                ganadores.add(ganador);
            }

        }


    }

}
