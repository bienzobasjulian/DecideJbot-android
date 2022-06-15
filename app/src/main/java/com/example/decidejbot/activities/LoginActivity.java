package com.example.decidejbot.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.decidejbot.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginActivity extends Activity {

    private Button btnLogin, btnRegistrarse, btnLoginSinSesion;
    private TextInputEditText edtMail, edtPassword;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.button_iniciarSesion);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);
        edtMail = (TextInputEditText) findViewById(R.id.mail_edit_text);
        edtPassword = (TextInputEditText) findViewById(R.id.password_edit_text);
        btnLoginSinSesion = (Button) findViewById(R.id.btnLoginSinSesion);

         firebaseAuth = FirebaseAuth.getInstance();



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = edtMail.getText().toString();
                String password = edtPassword.getText().toString();

                if (TextUtils.isEmpty(mail)){
                    edtMail.setError("Campo obligatorio");
                    edtMail.requestFocus();
                }
                else if (TextUtils.isEmpty(password)){
                    edtPassword.setError("Campo obligatorio");
                    edtPassword.requestFocus();
                }
                else {
                    firebaseAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnLoginSinSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
