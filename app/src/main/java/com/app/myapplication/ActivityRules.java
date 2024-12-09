package com.app.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;






public class ActivityRules extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_rules);

        Button btn = findViewById(R.id.btn_back);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ActivityRules.this, ActivityDashboard.class);
                startActivity(intent);
            }
        });

        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        MapView mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        double ipSantoTomasLatitud = 42.4072;
        double ipSantoTomasLongitud = -71.382;

        GeoPoint Massachusetts = new GeoPoint(ipSantoTomasLatitud, ipSantoTomasLongitud);
        mapView.getController().setZoom(15.0);
        mapView.getController().setCenter(Massachusetts);

        Marker marcadorMassachusetts = new Marker(mapView);
        marcadorMassachusetts.setPosition(Massachusetts);
        marcadorMassachusetts.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marcadorMassachusetts.setTitle("Estado de Massachusetts");
        marcadorMassachusetts.setSnippet("Arkham");


    }
}
