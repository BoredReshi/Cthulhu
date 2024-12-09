package com.app.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ActivityNewUser extends AppCompatActivity {





    private EditText txtUsuario, txtCorreo, txtPassword;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_newuser);

        db = FirebaseFirestore.getInstance();

        txtUsuario = findViewById(R.id.txt_usuario);
        txtCorreo = findViewById(R.id.txt_correo);
        txtPassword = findViewById(R.id.txt_pass);

        Button btn = findViewById(R.id.btn_back);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ActivityNewUser.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void enviarDatosFirestore(View view) {
        String usuario = txtUsuario.getText().toString();
        String correo = txtCorreo.getText().toString();
        String password = txtPassword.getText().toString();

        Map<String, Object> user = new HashMap<>();
        user.put("usuario", usuario);
        user.put("correo", correo);
        user.put("password", password);

        db.collection("user")
                .document(usuario)
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(ActivityNewUser.this, "Datos enviados a Firestore correctamente", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ActivityNewUser.this, "Error al enviar datos a Firestore: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }





}
