package com.example.decidejbot.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.decidejbot.R;
import com.example.decidejbot.classes.Participante;
import com.example.decidejbot.classes.Sorteo;
import com.example.decidejbot.controller.ParticipanteController;
import com.example.decidejbot.controller.SorteoController;
import com.example.decidejbot.dialogs.LoadingResultadosDialog;
import com.example.decidejbot.dialogs.ResultadosSorteoSimpleDialog;
import com.example.decidejbot.factory.ListaParticipantesFactory;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class SorteoSimple extends AppCompatActivity{

    private EditText edtaddParticipante, edtNumPremios;
    private ImageButton btnAddParticipante, btnMore;
    private ChipGroup chipGroup;
    private ArrayList<String> participantes = new ArrayList<>();
    private LoadingResultadosDialog loadingResultadosDialog;
    private ResultadosSorteoSimpleDialog resultadosDialog;
    //private ListaParticipantesDialog listaParticipantesDialog;
    private ExtendedFloatingActionButton btnSortear;
    private TextView txtContadorParticipantes;
    private ConstraintLayout layout;
    private CoordinatorLayout snackBarLayout;
    private SorteoController sorteoController = new SorteoController();
    private ParticipanteController participanteController = new ParticipanteController();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorteo_simple);




        edtaddParticipante = (EditText) findViewById(R.id.edt_addParticipante);
        edtNumPremios = (EditText) findViewById(R.id.edtNumPremios);
        btnAddParticipante = (ImageButton) findViewById(R.id.btn_AddParticipante);
        chipGroup = (ChipGroup) findViewById(R.id.chipGroupSorteoSimple);
        btnSortear = (ExtendedFloatingActionButton)  findViewById(R.id.btnSortear);
        txtContadorParticipantes = (TextView) findViewById(R.id.txt_contadorParticipantes);
        layout = (ConstraintLayout) findViewById(R.id.layoutSorteoSimple);
        snackBarLayout = (CoordinatorLayout) findViewById(R.id.snackbar_layout);
        btnMore = (ImageButton) findViewById(R.id.btnMoreSorteoSimple);







        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSheet();
            }
        });


       loadingResultadosDialog = new LoadingResultadosDialog(SorteoSimple.this);

        btnAddParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addParticipante();
            }
        });

        btnSortear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortear();




            }
        });

        edtaddParticipante.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {

                            addParticipante();

                    return true;

                }
                return false;
            }
        });



    }

    public void addParticipante(){
        String valueParticipante = edtaddParticipante.getText().toString();

        //Comprobar si el participante es valido
        boolean isValid = participanteValido(valueParticipante);

        if (isValid)
        {
            // Si es valido, creamos el participante
            createParticipante();
            txtContadorParticipantes.setText(participantes.size() + " participantes disponibles");
        }
        else
        {
            edtaddParticipante.setText("");
        }



    }

    public boolean participanteValido(String valorParticipante)
    {
        boolean isValid = false;
        //Recibe el participante y comprueba si es válido o no

        //Comprueba que el texto del edit text no esté vacio

        if (valorParticipante.trim().length() > 0)
        {
            //Comprobar que el participante no está ya en el array
            if (!participantes.contains(valorParticipante))
            {
                isValid = true;
            }
        }

        return isValid;
    }
    public void createParticipante(){
        Chip chip = new Chip(this);
        ChipDrawable drawable = ChipDrawable.createFromAttributes(this, null, 0,R.style.Widget_MaterialComponents_Chip_Entry);

        chip.setChipDrawable(drawable);
        chip.setCheckable(false);
        chip.setClickable(false);
        chip.setPadding(60,10,60,10);
        chip.setText(edtaddParticipante.getText().toString());

        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chipGroup.removeView(chip);
                participantes.remove(chip.getText().toString());
                txtContadorParticipantes.setText(participantes.size() + " participantes disponibles");


            }
        });

        chipGroup.addView(chip);
        participantes.add(edtaddParticipante.getText().toString());
        edtaddParticipante.setText("");






    }



    public void sortear(){
        String numPremiosS = edtNumPremios.getText().toString();
        int numPremios = 0;

        if (numPremiosS.isEmpty())
        {
             numPremios = participantes.size();
        }
        else
        {

             numPremios = Integer.parseInt(numPremiosS);
        }

        if (numPremios > participantes.size()){
           // Snackbar.make(layout, "Hay más premios que participantes", Snackbar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.red)).setAnchorView(btnSortear).show();
            Snackbar snackbar = Snackbar.make(layout, "Hay más premios que participantes" , Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(getResources().getColor(R.color.red));
            snackbar.setAnchorView(btnSortear);
            snackbar.show();

        }
        else
        {
            if (participantes.size() > 0)
            {
                loadingResultadosDialog.startLoadingDialog();
                Handler handler = new Handler();
                int finalNumPremios = numPremios;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingResultadosDialog.dimissDialog();






                        resultadosDialog = new ResultadosSorteoSimpleDialog(SorteoSimple.this, participantes, finalNumPremios);
                        resultadosDialog.mostrarResultados();


                    }
                }, 6000);
            }
            else
            {
                //Toast.makeText(getApplicationContext(), "Por lo menos debe haber un participante", Toast.LENGTH_SHORT).show();
                Snackbar snackbar = Snackbar.make(layout, "Por lo menos debe haber un participante" , Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(getResources().getColor(R.color.red));
                snackbar.setAnchorView(btnSortear);
                snackbar.show();
                //Snackbar.make(layout, "Por lo menos debe haber un participante", Snackbar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.red)).setAnchorView(btnSortear).show();

            }
        }



    }

    private void showSheet(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet_sorteo_simple);


        LinearLayout guardarLayout = dialog.findViewById(R.id.optionGuardarListaSorteoSimple);
        LinearLayout cargarLayout = dialog.findViewById(R.id.optionCargarListaSorteoSimple);
        LinearLayout eliminarLayout = dialog.findViewById(R.id.optionEliminarParticipantes);

        eliminarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                participantes.clear();

                dialog.dismiss();
                chipGroup.removeAllViews();
                txtContadorParticipantes.setText(participantes.size() + " participantes disponibles");

            }
        });

        cargarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        guardarLayout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {


                if (participantes.size() == 0)
                {
                    Snackbar snackbar = Snackbar.make(layout, "Por lo menos debe haber un participante" , Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(getResources().getColor(R.color.red));
                    snackbar.setAnchorView(btnSortear);
                    dialog.dismiss();
                    snackbar.show();


                }
                else
                {
                    //Crear el objeto Sorteo
                    Sorteo nuevoSorteo = sorteoController.create();


                    //Crear y juntar los objetos Participante
                    ArrayList<Participante> listaDeParticipantes = participanteController.createList(participantes, nuevoSorteo);

                    //Almacenar sorteo y participantes de este sorteo en la base de datos local
                   sorteoController.save(nuevoSorteo);
                   participanteController.save(listaDeParticipantes);
                   dialog.dismiss();






                }
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    public void onListaSeleccionada(String listaS)
    {
        ListaParticipantesFactory listaParticipantesFactory = new ListaParticipantesFactory();

        List<String> lista = listaParticipantesFactory.StringToListOfStrings(listaS);

        for (String s : lista){
            //Añadir participantes
            Chip chip = new Chip(this);
            ChipDrawable drawable = ChipDrawable.createFromAttributes(this, null, 0,R.style.Widget_MaterialComponents_Chip_Entry);

            chip.setChipDrawable(drawable);
            chip.setCheckable(false);
            chip.setClickable(false);
            chip.setPadding(60,10,60,10);
            chip.setText(s);
            participantes.add(s);

            txtContadorParticipantes.setText(participantes.size() + " participantes disponibles");

            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    chipGroup.removeView(chip);
                    participantes.remove(chip.getText().toString());
                    txtContadorParticipantes.setText(participantes.size() + " participantes disponibles");



                }
            });

            chipGroup.addView(chip);

        }


    }
        /*
    public void mostrarListas(List<ListaParticipantes> listaListas){

        AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());

        builder.setTitle("Cargar lista de participantes");

        ListaParticipantesFactory listaParticipantesFactory = new ListaParticipantesFactory();

        String[] lista= listaParticipantesFactory.ListOfStringToArrayString(listaListas);


        builder.setItems(lista, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

    }
*/

}