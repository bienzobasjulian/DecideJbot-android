package com.example.decidejbot.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.decidejbot.R;
import com.example.decidejbot.classes.Participante;
import com.example.decidejbot.classes.Sorteo;
import com.example.decidejbot.controller.ParticipanteController;
import com.example.decidejbot.controller.SorteoController;
import com.example.decidejbot.dialogs.ListaParticipantesDialog;
import com.example.decidejbot.dialogs.LoadingResultadosDialog;
import com.example.decidejbot.dialogs.ResultadosSorteoSimpleDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SorteoSimple extends AppCompatActivity{

    private EditText edtaddParticipante, edtNumPremios;
    private ImageButton btnAddParticipante, btnMore;
    private ChipGroup chipGroup;
    private ArrayList<String> participantes = new ArrayList<>();
    private LoadingResultadosDialog loadingResultadosDialog;
    private ResultadosSorteoSimpleDialog resultadosDialog;
    private ListaParticipantesDialog listaParticipantesDialog;
    private ExtendedFloatingActionButton btnSortear;
    private TextView txtContadorParticipantes;
    private ConstraintLayout layout;
    private CoordinatorLayout snackBarLayout;
    private SorteoController sorteoController = new SorteoController();
    private ParticipanteController participanteController = new ParticipanteController();
    private EditText edtTitleSorteo;
    private FirebaseFirestore fireDb;
    private FirebaseAuth firebaseAuth;
    public List<Sorteo> sorteosExternos;
    private CheckBox checkBoxCuentaAtras;
    private MediaPlayer mediaPlayer;






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
        edtTitleSorteo = (EditText) findViewById(R.id.title_SorteoSimple);
        checkBoxCuentaAtras = (CheckBox) findViewById(R.id.checkBoxCuentaAtras);
        fireDb = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        sorteosExternos = new ArrayList<>();











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
                int finalNumPremios = numPremios;

                if (checkBoxCuentaAtras.isChecked()){
                    loadingResultadosDialog.startLoadingDialog();
                    Handler handler = new Handler();


                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingResultadosDialog.dimissDialog();






                            resultadosDialog = new ResultadosSorteoSimpleDialog(SorteoSimple.this, participantes, finalNumPremios);
                            resultadosDialog.mostrarResultados();


                        }
                    }, 6000);
                }
                else {
                    resultadosDialog = new ResultadosSorteoSimpleDialog(SorteoSimple.this, participantes, finalNumPremios);
                    resultadosDialog.mostrarResultados();
                }

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


                //Obtener las listas de sorteos locales
                List<Sorteo> listasLocales = sorteoController.getAll();




                //System.out.println("Hay " + sorteosExternos.size() + " sorteos externos");





                if (listasLocales.size() == 0){

                    //Si no hay listas...

                    Snackbar snackbar = Snackbar.make(layout, "No hay listas de participantes guardadas" , Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(getResources().getColor(R.color.red));
                    snackbar.setAnchorView(btnSortear);
                    dialog.dismiss();
                    snackbar.show();


                }
                else {
                    listaParticipantesDialog = new ListaParticipantesDialog(SorteoSimple.this, listasLocales);
                    listaParticipantesDialog.mostrarListas(listasLocales);


                }


            }
        });

        guardarLayout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                    showSaveSheet();
                    dialog.dismiss();







                }

        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    private void cargarSorteosLocales(){

    }



    private void showSaveSheet(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet_guardar_sorteo);




        LinearLayout saveLocalLayout = dialog.findViewById(R.id.optionGuardarSorteoLocalmente);
        LinearLayout saveMixLayout = dialog.findViewById(R.id.optionGuardarSorteoMix);
        LinearLayout saveExternalLayout = dialog.findViewById(R.id.optionGuardarSorteoExternamente);

        saveLocalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveSorteoLocalmente(dialog);


            }
        });

        saveExternalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSorteoExternamente(dialog);

            }
        });

        saveMixLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSorteoLocalmente(dialog);
                saveSorteoExternamente(dialog);


            }
        });



        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    public void saveSorteoLocalmente(Dialog dialog){



        if (participantes.size() == 0)
        {
            dialog.dismiss();
            Snackbar snackbar = Snackbar.make(layout, "Por lo menos debe haber un participante" , Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(getResources().getColor(R.color.red));
            snackbar.setAnchorView(btnSortear);

            snackbar.show();


        }
        else
        {
            //Crear el objeto Sorteo
            String titleSorteo = edtTitleSorteo.getText().toString();

            if (titleSorteo.trim().length() == 0){
                titleSorteo = "Sorteo simple";
                edtTitleSorteo.setText(titleSorteo);
            }

            Sorteo nuevoSorteo = sorteoController.create(titleSorteo);


            //Crear y juntar los objetos Participante
            ArrayList<Participante> listaDeParticipantes = participanteController.createList(participantes, nuevoSorteo);

            //Almacenar sorteo y participantes de este sorteo en la base de datos local
            sorteoController.save(nuevoSorteo);
            participanteController.save(listaDeParticipantes);

            dialog.dismiss();
            Toast.makeText(getApplicationContext(), "Sorteo almacenado localmente", Toast.LENGTH_SHORT).show();


        }
    }

    public void saveSorteoExternamente(Dialog dialog){

        //Comprobar si hay sesión iniciada
        if (firebaseAuth.getCurrentUser() == null){
            Toast.makeText(getApplicationContext(), "Esta acción requiere tener sesión iniciada", Toast.LENGTH_SHORT).show();
        }
        else{

            String idSorteo = UUID.randomUUID().toString();

            Map<String, Object> sorteo = new HashMap<>();
            sorteo.put("id", idSorteo);
            String titleSorteo = edtTitleSorteo.getText().toString().trim();
            sorteo.put("titulo", titleSorteo);
            sorteo.put("participantes", participantes);
            //String idUsuario = firebaseAuth.getCurrentUser().getUid();
            DocumentReference usuarioReference = fireDb.collection("Usuarios").document(firebaseAuth.getCurrentUser().getUid());
            sorteo.put("usuario", usuarioReference);



            fireDb.collection("sorteos").document(idSorteo).set(sorteo)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Sorteo almacenado externamente", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Error al almacenar el sorteo externamente", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void addPartipantesSeleccionados(Sorteo sorteo){

        List<Participante> participantesOfSorteo = sorteo.getParticipantes();
        System.out.println("Hay " + participantesOfSorteo.size());

        for (int i = 0; i < participantesOfSorteo.size(); i++){

            Chip chip = new Chip(this);
            ChipDrawable drawable = ChipDrawable.createFromAttributes(this, null, 0,R.style.Widget_MaterialComponents_Chip_Entry);

            chip.setChipDrawable(drawable);
            chip.setCheckable(false);
            chip.setClickable(false);
            chip.setPadding(60,10,60,10);
            chip.setText(participantesOfSorteo.get(i).getValor());

            boolean participanteExiste = false;

            for (int j = 0; j < participantes.size(); j ++){
                if (participantes.get(j).equals(participantesOfSorteo.get(i).getValor())){
                    participanteExiste = true;
                    break;
                }
            }
            if (!participanteExiste){
                participantes.add(participantesOfSorteo.get(i).getValor());
                chipGroup.addView(chip);
            }


            txtContadorParticipantes.setText(participantes.size() + " participantes disponibles");

            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    chipGroup.removeView(chip);
                    participantes.remove(chip.getText().toString());
                    txtContadorParticipantes.setText(participantes.size() + " participantes disponibles");



                }
            });



        }
    }

    public void saveResultados(ArrayList<String> ganadores, Dialog dialog){

        //Comprobar si hay sesión iniciada
        if (firebaseAuth.getCurrentUser() == null){
            Toast.makeText(getApplicationContext(), "Esta acción requiere tener sesión iniciada", Toast.LENGTH_SHORT).show();
        }
        else{
            String idSorteo = UUID.randomUUID().toString();

            Map<String, Object> sorteo = new HashMap<>();
            sorteo.put("id", idSorteo);
            String titleSorteo = edtTitleSorteo.getText().toString().trim();
            sorteo.put("titulo", titleSorteo);
            sorteo.put("participantes", participantes);
            //String idUsuario = firebaseAuth.getCurrentUser().getUid();
            DocumentReference usuarioReference = fireDb.collection("Usuarios").document(firebaseAuth.getCurrentUser().getUid());
            sorteo.put("usuario", usuarioReference);



            fireDb.collection("sorteos").document(idSorteo).set(sorteo)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(getApplicationContext(), "Sorteo almacenado externamente", Toast.LENGTH_SHORT).show();
                            Date fechaActual = new Date();
                           String idResultados = UUID.randomUUID().toString();
                            Map<String, Object> resultado = new HashMap<>();
                            resultado.put("id", idResultados);
                            resultado.put("fecha", fechaActual);
                            resultado.put("ganadores", ganadores);
                            resultado.put("usuario", usuarioReference);

                            DocumentReference sorteoReference = fireDb.collection("sorteos").document(idSorteo);
                            resultado.put("sorteo", sorteoReference);

                            fireDb.collection("resultados").document(idResultados).set(resultado)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getApplicationContext(), "Resultado almacenado externamente", Toast.LENGTH_SHORT).show();
                                            //Notificación para acceder a los resultados online
                                            String canalID = "Canal 01";

                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                                            {
                                                CharSequence canalNombre = "Notificacion";
                                                NotificationChannel miCanal = new NotificationChannel(canalID, canalNombre,
                                                        NotificationManager.IMPORTANCE_DEFAULT);
                                                NotificationManager notificador = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                                notificador.createNotificationChannel(miCanal);
                                            }
                                            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), canalID);
                                            builder.setSmallIcon(R.drawable.logo300);
                                            builder.setSubText("DecideJbot");
                                            //builder.setLargeIcon(icon);
                                            builder.setContentTitle("Resultados públicados");
                                            builder.setContentText("Pulsa para ver los resultados de tu sorteo en la web");

                                            String url = "https://bienzobasjulian.github.io/DecideJbot-WEB-Angular-/#/sorteos/resultado/" + idResultados;

                                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),
                                                    0,i,PendingIntent.FLAG_UPDATE_CURRENT);
                                            builder.setContentIntent(pi);

                                            int notificacionID = 0;
                                            NotificationManagerCompat notificacion = NotificationManagerCompat.from(getApplicationContext());
                                            notificacion.notify(notificacionID, builder.build());

                                            dialog.dismiss();


                                        }
                                    });



                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(), "Error al almacenar el sorteo externamente", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }







    public void replaceParticipantes(Sorteo sorteo){
        chipGroup.removeAllViews();
        participantes.clear();


        List<Participante> participantesOfSorteo = sorteo.getParticipantes();



        for (int i = 0; i < participantesOfSorteo.size(); i++){

            Chip chip = new Chip(this);
            ChipDrawable drawable = ChipDrawable.createFromAttributes(this, null, 0,R.style.Widget_MaterialComponents_Chip_Entry);

            chip.setChipDrawable(drawable);
            chip.setCheckable(false);
            chip.setClickable(false);
            chip.setPadding(60,10,60,10);
            chip.setText(participantesOfSorteo.get(i).getValor());


            participantes.add(participantesOfSorteo.get(i).getValor());

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


}