package com.example.decidejbot.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.decidejbot.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private Button btnLogin, btnRegistrarse;
    private TextInputEditText edtMail, edtPassword, edtPassword2, edtNombre, edtApellidos, edtTelefono;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore fireDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnLogin = (Button) findViewById(R.id.button_iniciarSesion);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);
        edtMail = (TextInputEditText) findViewById(R.id.mail_edit_text);
        edtPassword = (TextInputEditText) findViewById(R.id.password_edit_text);
        edtPassword2 = (TextInputEditText) findViewById(R.id.password2_edit_text);
        edtNombre = (TextInputEditText) findViewById(R.id.name_edit_text);
        edtApellidos = (TextInputEditText) findViewById(R.id.surname_edit_text);
        edtTelefono = (TextInputEditText) findViewById(R.id.phone_edit_text);

        firebaseAuth = FirebaseAuth.getInstance();
        fireDb = FirebaseFirestore.getInstance();

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = edtMail.getText().toString().trim();
                String password = edtPassword.getText().toString();
                String password2 = edtPassword2.getText().toString();
                String nombre = edtNombre.getText().toString();
                String apellidos = edtApellidos.getText().toString();
                String telefono = edtTelefono.getText().toString();




                boolean formIsOk = false;

                if (mail.trim().length() == 0){
                    edtMail.setError("Campo obligatorio");
                    edtMail.requestFocus();
                }
                else if (password.trim().length() == 0){
                    edtPassword.setError("Campo obligatorio");
                    edtPassword.requestFocus();
                }
                else if (!password.equals(password2) ){
                    edtPassword2.setError("Las contraseñas no coinciden");
                    edtPassword2.requestFocus();
                }
                else if (nombre.trim().length() == 0){
                    edtNombre.setError("Campo obligatorio");
                    edtNombre.requestFocus();
                }
                else if (apellidos.trim().length() == 0){
                    edtApellidos.setError("Campo obligatorio");
                    edtApellidos.requestFocus();
                }
                else{
                    formIsOk = true;
                }




                if (formIsOk){
                    firebaseAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                String userID = firebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fireDb.collection("Usuarios").document(userID);

                                Map<String, Object> usuario = new HashMap<>();
                                usuario.put("id", userID);
                                usuario.put("email", mail);
                                usuario.put("nombre", nombre);
                                usuario.put("apellidos", apellidos);

                                if (telefono.length() > 0){
                                    usuario.put("telefono", telefono);
                                }


                                documentReference.set(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });



                            }
                            else{
                                Toast.makeText(getApplicationContext(), "No ha sido posible registrarse", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }




            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}