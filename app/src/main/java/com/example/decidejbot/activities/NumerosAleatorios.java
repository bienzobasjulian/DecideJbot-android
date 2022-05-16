package com.example.decidejbot.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.decidejbot.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumerosAleatorios extends AppCompatActivity {

    private EditText inputMinimo, inputMaximo, inputCantidadNumeros;
    private MaterialButton btnGenerar;
    private View containerResultados;
    private TextView txtResultados;
    private CheckBox checkRepetir;
    private boolean checkIsChecked;
    private ConstraintLayout layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros_aleatorios);
        inputMinimo = (EditText) findViewById(R.id.inputMinimo);
        inputMaximo = (EditText) findViewById(R.id.inputMaximo);
        inputCantidadNumeros = (EditText) findViewById(R.id.edt_CantidadNumeros);
        checkRepetir = (CheckBox) findViewById(R.id.chck_repiteNumeros);
        layout = (ConstraintLayout) findViewById(R.id.layoutNumerosAleatorios);
        btnGenerar = (MaterialButton)  findViewById(R.id.btnGenerar);
        txtResultados = (TextView) findViewById(R.id.txtResultados);
        containerResultados = findViewById(R.id.containerResultados);

        checkIsChecked = false;
        
        checkRepetir.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    checkIsChecked = true;
                }
                else {
                    checkIsChecked = false;
                }
            }
        });

        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate();
            }
        });

    }

    private void generate(){
        if (verifiyForm())
        {
            int minimo = Integer.parseInt(inputMinimo.getText().toString());
            int maximo = Integer.parseInt(inputMaximo.getText().toString());
            int cantidadNumeros = Integer.parseInt(inputCantidadNumeros.getText().toString());

            ArrayList<Integer> numerosGenerados = getNumbers(minimo, maximo, cantidadNumeros, checkIsChecked);
            containerResultados.setVisibility(View.VISIBLE);
            String resultadosS = getResultadosString(numerosGenerados);
            txtResultados.setText(resultadosS);
        }




    }

    private ArrayList<Integer> getNumbers(int min, int max, int cantidad, boolean canRepeat){
        ArrayList<Integer> numeros = new ArrayList<>();
        ArrayList<Integer> numerosExcluidos = new ArrayList<>();

        int numerosAnyadidos = 0;

        while (numerosAnyadidos < cantidad)
        {
            int numero = generateRandomNumberInRange(min, max);


            if (!numerosExcluidos.contains(numero)){
                numeros.add(numero);
                if (!canRepeat)
                {
                    numerosExcluidos.add(numero);
                }
                numerosAnyadidos++;
            }




        }

        return numeros;

    }

    private int generateRandomNumberInRange(int min, int max){
        Random random = new Random();
        int numeroAleatorio = random.nextInt((max - min) + 1) + min;
        return numeroAleatorio;
    }

    private boolean verifiyForm(){
        String minimoS = inputMinimo.getText().toString();
        String maximoS = inputMaximo.getText().toString();
        String cantidadS = inputCantidadNumeros.getText().toString();
        boolean formIsOk = false;



        //Si alguno de los campos está vacio
        if (minimoS.isEmpty() || maximoS.isEmpty() || cantidadS.isEmpty()){
            Snackbar snackbar = Snackbar.make(layout, "Completa todos los campos" , Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(getResources().getColor(R.color.red));
            snackbar.show();

            return formIsOk;
        }
        else if(Integer.parseInt(maximoS) < Integer.parseInt(minimoS)){
            Snackbar snackbar = Snackbar.make(layout, "El nº mínimo debe ser menor al nº máximo" , Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(getResources().getColor(R.color.red));
            snackbar.show();
            return formIsOk;
        }
        else if(Integer.parseInt(cantidadS) <= 0)
        {
            Snackbar snackbar = Snackbar.make(layout, "Por lo menos se debe generar un número" , Snackbar.LENGTH_SHORT);
            snackbar.setBackgroundTint(getResources().getColor(R.color.red));
            snackbar.show();
            return formIsOk;
        }


        if (!checkIsChecked)
        {
            int numerosDisponibles = Integer.parseInt(maximoS) - Integer.parseInt(minimoS) + 1;

            if (numerosDisponibles < Integer.parseInt(cantidadS)){
                Snackbar snackbar = Snackbar.make(layout, "No se pueden generar tantos números" , Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(getResources().getColor(R.color.red));
                snackbar.show();
                return formIsOk;
            }
        }
        formIsOk = true;
        return  formIsOk;
    }

    private String getResultadosString(ArrayList<Integer> numeros){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Números: ");

        for (int i = 0; i < numeros.size(); i++){
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(String.valueOf(numeros.get(i)));
        }

        return stringBuilder.toString();
    }
}