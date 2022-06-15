package com.example.decidejbot.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.decidejbot.BuildConfig;
import com.example.decidejbot.R;
import com.example.decidejbot.fragments.FragmentoDecisiones;
import com.example.decidejbot.fragments.FragmentoHome;
import com.example.decidejbot.fragments.FragmentoSorteos;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity  {

    FragmentoSorteos fragmentoSorteos = new FragmentoSorteos();
    FragmentoDecisiones fragmentoDecisiones = new FragmentoDecisiones();
    FragmentoHome fragmentoHome = new FragmentoHome();
    BottomNavigationView bottomNavigationView;
    FloatingActionButton btnInicio;
    Fragment fragmentoAbierto = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(fragmentoHome);
        fragmentoAbierto = fragmentoHome;




        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setSelectedItemId(R.id.holder);

        switch (isFirstTime())
        {
            case 0:
                mostrarDialogoNovedades();
                break;
            case 2:
                mostrarDialogoNovedades();
                break;
        }




        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btnInicio = (FloatingActionButton) findViewById(R.id.btnInicio);

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationView.setSelectedItemId(R.id.holder);

                    loadFragment(fragmentoHome);
                    fragmentoAbierto = fragmentoHome;





            }
        });

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.sorteos:
                    loadFragment(fragmentoSorteos);
                    fragmentoAbierto = fragmentoSorteos;
                    return true;

                case R.id.decisiones:
                    loadFragment(fragmentoDecisiones);
                    fragmentoAbierto = fragmentoDecisiones;
                    return true;

            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();

    }

    private void closeFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment).commit();

    }

    private void mostrarDialogoNovedades(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Novedades de la beta 22.06.08");
        builder.setMessage("- Solución de bugs en la carga de sorteos" );
        builder.show();
    }

    private int isFirstTime(){
        SharedPreferences sp = getSharedPreferences("MYAPP", 0);
        int result, currentVersionCode = BuildConfig.VERSION_CODE;
        int lastVersionCode = sp.getInt("FIRSTTIMERUN", -1);
        if (lastVersionCode == -1) result = 0; else
            result = (lastVersionCode == currentVersionCode) ? 1 : 2;
        sp.edit().putInt("FIRSTTIMERUN", currentVersionCode).apply();
        return result;

    }



}