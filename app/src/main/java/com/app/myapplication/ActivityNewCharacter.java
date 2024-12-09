package com.app.myapplication;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;




public class ActivityNewCharacter extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;

    EditText editTextName;
    Button buttonAdd;
    ListView listView;
    DatabaseHelper dbHelper;
    ArrayList<String> itemList;
    ArrayAdapter<String> adapter;
    int selectedItemID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcharacter);


    /*
        loadData();

        buttonAdd.setOnClickListener(v -> {
            String nam = editTextName.getText() .toString();
            if(name.isEmpty()) {
                Toast.makeText(this, "Complete el campo de nombre para guardar", Toast.LENGTH_SHORT).show();
            } else {
                long id = dbHelper.insertItem(name);
                if(id != -1) {
                    Toast.makeText(this,"Item agregado", Toast.LENGTH_SHORT).show();
                    loadData();
                } else {
                    Toast.makeText(this,"Error al agregar", Toast.LENGTH_SHORT).show();
                }
            }
        }
     */



        imageView = findViewById(R.id.imageView);
        Button select = findViewById(R.id.btn_selectimage);
        Button delete = findViewById(R.id.btn_removeimage);


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageDrawable(null);
                Toast.makeText(ActivityNewCharacter.this, "Imagen eliminada", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }



}

