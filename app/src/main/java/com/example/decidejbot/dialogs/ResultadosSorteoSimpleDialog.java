package com.example.decidejbot.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.decidejbot.R;
import com.example.decidejbot.activities.SorteoSimple;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ResultadosSorteoSimpleDialog {

    Activity activity;
    AlertDialog dialog;

    ArrayList<String> participantes;
    ArrayList<String> ganadores;
    int numPremios;
    ListView listView;
    TextView txtFecha;
    MaterialButton btnGuardarResultados;
    private MediaPlayer mediaPlayer;

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
        txtFecha = (TextView) view.findViewById(R.id.txt_fechaSorteo);
        btnGuardarResultados = (MaterialButton) view.findViewById(R.id.btnGuardarResultados);



        Date date = Calendar.getInstance().getTime();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy hh:mm:ss");
        String fecha = dateFormat.format(date);

        txtFecha.setText(fecha);




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity.getApplicationContext(),
                android.R.layout.simple_list_item_1, ganadores);
        listView.setAdapter(adapter);


        builder.setView(view);

        dialog = builder.create();
        btnGuardarResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity instanceof SorteoSimple){
                    ((SorteoSimple) activity).saveResultados(ganadores, dialog);
                }
            }
        });
        dialog.show();

        mediaPlayer = MediaPlayer.create(activity.getApplicationContext(), R.raw.victory);
        mediaPlayer.start();


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
