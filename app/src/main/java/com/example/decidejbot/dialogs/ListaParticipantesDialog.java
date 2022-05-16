/*
package com.example.decidejbot.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.decidejbot.R;
import com.example.decidejbot.activities.SorteoSimple;

import java.util.ArrayList;
import java.util.List;

public class ListaParticipantesDialog {

    Activity activity;
    AlertDialog dialog;
    //List<ListaParticipantes> listas;



    public ListaParticipantesDialog(Activity myActivity, List<ListaParticipantes> listas){
        this.activity = myActivity;
        this.listas = listas;
    }


    public void mostrarListas(List<ListaParticipantes> listaListas) {



        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_listas_participantes,null);



      ListView listView = (ListView) view.findViewById(R.id.listaListasParticipantes);

        ArrayList<String> listasS = getListasS(listaListas);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity.getApplicationContext(),
                android.R.layout.simple_list_item_1, listasS);
        listView.setAdapter(adapter);

        builder.setView(view);

        dialog = builder.create();
        dialog.show();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //String listaSeleccionada = listaListas.get(position).getParticipantesS();

                if (activity instanceof SorteoSimple) {
                    String listaSeleccionada = listaListas.get(position).getParticipantesS();
                    ((SorteoSimple) activity).onListaSeleccionada(listaSeleccionada);
                    dialog.dismiss();
                }


            }

        });








    }






    public ArrayList<String> getListasS(List<ListaParticipantes> listaListas)
    {
        ArrayList<String> listasS = new ArrayList<>();
        for (int i = 0; i < listaListas.size(); i++){
            //Recorremos el array de listas
            listasS.add(listaListas.get(i).getParticipantesS());
        }

        return listasS;
    }
}
*/
