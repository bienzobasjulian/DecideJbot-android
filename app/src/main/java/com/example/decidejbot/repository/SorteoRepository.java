package com.example.decidejbot.repository;

import androidx.annotation.NonNull;

import com.example.decidejbot.classes.Participante;
import com.example.decidejbot.classes.Sorteo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SorteoRepository
{
    private FirebaseFirestore fireDb = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public void save(Sorteo nuevoSorteo) {

        nuevoSorteo.save();
    }

    public List<Sorteo> getAll(){
        List<Sorteo> sorteos = Sorteo.listAll(Sorteo.class);




        return sorteos;
    }

    public void delete(Sorteo sorteoSeleccionado) {
        sorteoSeleccionado.delete();
    }


}
