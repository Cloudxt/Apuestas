package com.diurnohlc.apuestas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by cloud on 13/12/2017.
 */

public class Ajustes extends AppCompatActivity {
    private Resources res;
    private TabHost tab;
    final Integer[] cantidad = {1, 2, 5, 10};
    private ArrayAdapter<Integer> adaptador;
    private ArrayAdapter<String> adaptador2;
    private Spinner spinner,partidos;
    String deporte;
    int numero1, numero2;
    TextView num1, num2;
    String spinnervalor,numerofinal1 ;
    String numerofinal2 ;
    public Intent intent = new Intent();
    MiBaseDedatosHelper bdHelp;
    SQLiteDatabase bd;
    Cursor cur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustes);
        TextView tipodedeporte = (TextView) findViewById(R.id.tipodedeporte);
        partidos = (Spinner) findViewById(R.id.partidos);
        spinner = (Spinner) findViewById(R.id.spinnerapuesta);
        num1 = (TextView) findViewById(R.id.textnumero1);
        num2 = (TextView) findViewById(R.id.textnumero2);
        deporte = getIntent().getExtras().getString("ENVIARDEPORTE");

        //Esta parte ya no haria falta ya que escogemos el partido segun el spinner
//        switch (deporte) {
//            case "futbol":
//                tipodedeporte.setText("Futbol");
//                partidos.setText("Almería - Ciudad Real");
//                break;
//            case "tenis":
//                tipodedeporte.setText("Tenis");
//                partidos.setText("Nadal - Ferrer");
//                break;
//            case "balonmano":
//                tipodedeporte.setText("balonmano");
//                partidos.setText(" Granoller - Barcelona");
//                break;
//            case "baloncesto":
//                tipodedeporte.setText("baloncesto");
//                partidos.setText("Estudiantes - Barcelona");
//                break;
//
//        }
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


        spinner.setAdapter(adaptador);

        //

        //Aqui iniciamos el nuevo spinner con el array que le pasamos por parametro con los partidos en la bd
        adaptador2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, conseguirPartidos());

        adaptador2.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        partidos.setAdapter(adaptador2);



        PreferenceManager.setDefaultValues(this,R.xml.settings,true);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String preferenciaDeporte = sharedPref.getString("apuesta","2" );
        preferencias(preferenciaDeporte);




    }

    /**
     * Este metodo recorre la base de datos buscando todos los partidos posibles para añadirlos a un ArrayList
     * @return Un array list con todos los partidos posibles
     */
    private ArrayList<String> conseguirPartidos() {

        bdHelp = new MiBaseDedatosHelper(this, "partidos", null, 1);
        bd = bdHelp.getReadableDatabase();
        cur = bd.rawQuery("SELECT equipo1, equipo2 FROM partidos", null);
        final ArrayList<String> partidos= new ArrayList<>();
        if (bd.isOpen()) {

            if (cur.moveToFirst()) {
                do {
                    String equipo1 = cur.getString(0);
                    String equipo2 = cur.getString(1);
                    partidos.add(equipo1+" - "+equipo2);

                } while (cur.moveToNext());

            }
            bd.close();
            return partidos;
        }
        return null;
    }

    //Preferencias
    private void preferencias(String preferenciaDeporte) {
        if(preferenciaDeporte.equals("1")){
            spinner.setSelection(0);
        }
        if(preferenciaDeporte.equals("2")){
            spinner.setSelection(1);
        }
        if(preferenciaDeporte.equals("5")){
            spinner.setSelection(2);
        }
        if(preferenciaDeporte.equals("10")){
            spinner.setSelection(3);
        }
    }

    /**
     * Metodo para comprobar que todos los datos estan correctos y devuelven a la actividad principal los datos rellenados
     */
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
            Bundle bundle= new Bundle();
            spinnervalor= spinner.getSelectedItem().toString();
             numerofinal1 =String.valueOf(numero1);
             numerofinal2 =String.valueOf(numero2);
            bundle.putString("APUESTA",spinnervalor);
            bundle.putString("NUMERO1",numerofinal1);
            bundle.putString("NUMERO2",numerofinal2);
            intent.putExtras(bundle);
            setResult(RESULT_OK,intent);
            finish();
        } else {

        }
    }

    /**
     * Si se destruye la actividad guarda los datos
     * @param estado
     */
    protected void onSaveInstanceState(Bundle estado) {
        super.onSaveInstanceState(estado);
        estado.putString ("SPINNERAPUESTA", spinnervalor);
        estado.putString ("NUMERO1", numerofinal1);
        estado.putString ("NUMERO2", numerofinal2);
        estado.putInt("ESTADOTAB",tab.getCurrentTab());
    }

    /**
     * En el caso de recuperar la actividad vielve a poner los valores que habia en lso edittext
     * @param estado
     */
    @Override
    protected void onRestoreInstanceState(Bundle estado) {
        super.onRestoreInstanceState (estado);
        spinnervalor = estado.getString("SPINNERAPUESTA");
        numerofinal1= estado.getString("NUMERO1");
        numerofinal2 = estado.getString("NUMERO2");
        tab.setCurrentTab(estado.getInt("ESTADOTAB"));

    }
}