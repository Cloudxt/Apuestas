package com.diurnohlc.apuestas;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;

/**
 * Created by cloud on 13/12/2017.
 */

public class Ajustes extends AppCompatActivity {
    private Resources res;
    private TabHost tab;
    final Integer[] cantidad = {1, 2, 5, 10};
    private ArrayAdapter<Integer> adaptador;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustes);
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
}