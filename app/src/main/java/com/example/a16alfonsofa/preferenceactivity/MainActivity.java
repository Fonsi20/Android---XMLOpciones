package com.example.a16alfonsofa.preferenceactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnSave,btnopciones,btnsaber;
    private RadioButton rbAzul, rbRojo;
    private TextView lblT;
    private String color = "Negro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSave = (Button) findViewById(R.id.BTNSave);
        btnopciones = (Button) findViewById(R.id.BTNOpciones);
        btnsaber = (Button) findViewById(R.id.BTNSaberCosas);
        rbAzul = (RadioButton) findViewById(R.id.rbAzul);
        rbRojo = (RadioButton) findViewById(R.id.rbRojo);
        lblT = (TextView) findViewById(R.id.lblTexto);

        lblT.setText("Texto Cambiante");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        color = prefs.getString("Color", "");

        switch (color) {

            case "Rojo":
                lblT.setTextColor(Color.RED);
                break;
            case "Azul":
                lblT.setTextColor(Color.BLUE);
                break;
            default:
                lblT.setTextColor(Color.BLACK);
                break;
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Texto", "Texto Cambiante");

                if (rbAzul.isChecked()) {
                    lblT.setTextColor(Color.BLUE);
                    editor.putString("Color", "Azul");
                } else if (rbRojo.isChecked()) {
                    lblT.setTextColor(Color.RED);
                    editor.putString("Color", "Rojo");
                } else {
                    lblT.setTextColor(Color.BLACK);
                    editor.putString("Color", "Negro");
                }

                editor.apply();
            }
        });

        btnopciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Opciones.class);
                startActivity(i);
            }
        });

        btnsaber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                Log.i("Preferencias","opcion1: "+ pref.getBoolean("opcion1",false));
                Log.i("Preferencias","Pipo: "+ pref.getBoolean("pipooo",false));
                Log.i("Preferencias","Escribir: "+ pref.getString("opcion2",""));
                Log.i("Preferencias","Lista: "+ pref.getString("opcion3",""));
            }
        });
    }
}
