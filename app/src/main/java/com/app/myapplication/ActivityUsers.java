package com.app.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ActivityUsers extends AppCompatActivity {

    private ListView lista;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_users);

        lista = findViewById(R.id.Lista);
        db = FirebaseFirestore.getInstance();

        Button btn = findViewById(R.id.btn_back);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ActivityUsers.this, ActivityOptions.class);
                startActivity(intent);
            }
        });
    }

    public void CargarLista(View view) {
        CargarListaFirestore();
    }

    public void CargarListaFirestore() {
        db.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> datosLista = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String usuario = document.getString("usuario");
                                String correo = document.getString("correo");
                                String password = document.getString("password");

                                if (usuario != null && correo != null && password != null) {
                                    String linea = "||" + usuario + "||" + correo + "||" + password;
                                    datosLista.add(linea);
                                }
                            }

                            if (datosLista.isEmpty()) {
                                Log.d("TAG", "No hay datos que mostrar");
                            }

                            ArrayAdapter<String> adaptador = new ArrayAdapter<>(ActivityUsers.this,
                                    android.R.layout.simple_list_item_1, datosLista);
                            lista.setAdapter(adaptador);

                        } else {
                            Log.e("TAG", "Error al obtener datos de Firestore", task.getException());
                        }
                    }
                });
    }
}
