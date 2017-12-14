package com.diurnohlc.apuestas;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by cloud on 13/12/2017.
 */

public class Ajustes extends AppCompatActivity {
    private Resources res;
    private TabHost tab;
    final Integer[] cantidad = {1, 2, 5, 10};
    private ArrayAdapter<Integer> adaptador;
    private Spinner spinner;
    String deporte;
    int numero1, numero2;
    TextView num1, num2;
    public Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustes);
        TextView tipodedeporte = (TextView) findViewById(R.id.tipodedeporte);
        TextView partidos = (TextView) findViewById(R.id.partidos);
        num1 = (TextView) findViewById(R.id.textnumero1);
        num2 = (TextView) findViewById(R.id.textnumero2);

        deporte = getIntent().getExtras().getString("deporte");
        switch (deporte) {
            case "futbol":
                tipodedeporte.setText("Futbol");
                partidos.setText("Almería - Ciudad Real");
                break;
            case "tenis":
                tipodedeporte.setText("Tenis");
                partidos.setText("Nadal - Ferrer");
                break;
            case "balonmano":
                tipodedeporte.setText("balonmano");
                partidos.setText(" Granoller - Barcelona");
                break;
            case "baloncesto":
                tipodedeporte.setText("baloncesto");
                partidos.setText("Estudiantes - Barcelona");
                break;

        }
        Button btguardar = (Button) findViewById(R.id.botonguardar);
        btguardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                guardar();
            }
        });
        Button btguardar2 = (Button) findViewById(R.id.botonguardar2);
        btguardar2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                guardar();
            }
        });
        Button btvolver = (Button) findViewById(R.id.botonvolver);
        btvolver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button btvolver2 = (Button) findViewById(R.id.botonvolver2);
        btvolver2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        res = getResources();

        tab = (TabHost) findViewById(R.id.TabsHost);
        tab.setup();
        TabHost.TabSpec spec = tab.newTabSpec("dinero");
        spec.setContent(R.id.tabdinero);
        spec.setIndicator("Dinero");
        tab.addTab(spec);

        spec = tab.newTabSpec("Combinacion");
        spec.setContent(R.id.tabcombinacion);
        spec.setIndicator("Combinación");
        tab.addTab(spec);

        tab.setCurrentTab(0);

        adaptador = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, cantidad);

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.spinnerapuesta);
        spinner.setAdapter(adaptador);


    }

    public void guardar() {
        boolean comprobar = true;
        if (num1.getText().toString().equals("")||num2.getText().toString().equals("")) {
            comprobar = false;
            Toast.makeText(getApplicationContext(), R.string.nonumeros,
                    Toast.LENGTH_SHORT).show();
        } else {
            numero1 = Integer.parseInt(num1.getText().toString());
            numero2 = Integer.parseInt(num2.getText().toString());
            if (numero1 > 300 || numero2 > 300) {
                Toast.makeText(getApplicationContext(), R.string.nm,
                        Toast.LENGTH_SHORT).show();
                comprobar = false;
            }
        }
        if (spinner.getSelectedItem().equals("")) {
            comprobar = false;

        }
        if (comprobar) {
            Toast.makeText(getApplicationContext(), R.string.correcto,
                    Toast.LENGTH_SHORT).show();
            finish();
        } else {

        }
    }

}