package com.diurnohlc.apuestas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    final static int codigo_registro = 0;
    final static int codigo_apuesta = 1;
    final static int codigo_ajustes = 2;
    Button btapuestas, btajustes;
    String apuesta;
    String deporte;
    String nombre;
    boolean abrirajustes = false, abrirapuestas = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btregistro = (Button) findViewById(R.id.botonregistro);
        btapuestas = (Button) findViewById(R.id.botonapuestas);
        Button btsorteo = (Button) findViewById(R.id.botonsorteo);


        btajustes = (Button) findViewById(R.id.botonajustes);


        //sorteo y poner un toast
        btregistro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                abrirRegistro();
            }
        });
        btajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirAjustes();
            }
        });
        btsorteo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.adelante,
                        Toast.LENGTH_SHORT).show();
            }
        });
        btapuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirApuestas();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuprincipal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menuayuda) {
            Intent intent = new Intent(this, Ayuda.class);
            startActivity(intent);
            return true;
        } else {
            Intent intent = new Intent(this, Acercade.class);
            startActivity(intent);
            return true;
        }


    }

    public void abrirRegistro() {

        Intent intent = new Intent(this, Registro.class);


        startActivityForResult(intent, codigo_registro);

    }

    public void abrirApuestas() {
        if (abrirapuestas) {
            Intent intent = new Intent(this, Apuestas.class);
            startActivityForResult(intent, codigo_apuesta);
        } else {
            Toast.makeText(getApplicationContext(), R.string.registrarte,
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void abrirAjustes() {
        if (abrirajustes) {
            Intent intent = new Intent(this, Ajustes.class);
            intent.putExtra("deporte", apuesta);
            startActivityForResult(intent, codigo_ajustes);
        } else {
            Toast.makeText(getApplicationContext(), R.string.seleccionar,
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == codigo_registro) {
                obtenerDatosRegistro(data);
                abrirapuestas = true;
            } else if (requestCode == codigo_apuesta) {
                obtenerDatosApuesta(data);
                apuesta = data.getExtras().getString("apuesta");
                abrirajustes = true;
            }else if (requestCode==codigo_ajustes){
                Log.i("asdf","asdf");
                obtenerDatosAjustes(data);
            }


        }
    }

    public void obtenerDatosRegistro(Intent intent) {
        Bundle bundle = intent.getExtras();
        String toast = getResources().getString(R.string.nombre) +" "+ bundle.getString("NOMBRE")+" ";
        toast += getResources().getString(R.string.email) +" "+ bundle.getString("CORREO")+" ";
        toast += getResources().getString(R.string.fecha2) +" "+ bundle.getString("FECHA");
        Toast.makeText(getApplicationContext(), toast,
                Toast.LENGTH_LONG).show();
    }

    public void obtenerDatosApuesta(Intent intent) {
        Bundle bundle = intent.getExtras();
        deporte = bundle.getString("deporte");
        String toast = getResources().getString(R.string.nombre) +" "+ bundle.getString("DEPORTE");
        Toast.makeText(getApplicationContext(), toast,
                Toast.LENGTH_LONG).show();
    }
    public void obtenerDatosAjustes(Intent intent) {
        Bundle bundle = intent.getExtras();
        String toast = getResources().getString(R.string.apuestas) +" "+ bundle.getString("APUESTA")+" ";
        toast += getResources().getString(R.string.numero1) +" "+ bundle.getString("NUMERO1")+" ";
        toast += getResources().getString(R.string.numero2) +" "+ bundle.getString("NUMERO2");
        Toast.makeText(getApplicationContext(), toast,
                Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onSaveInstanceState(Bundle estado) {
        super.onSaveInstanceState(estado);
        estado.putBoolean ("ABRIRAPUESTAS", abrirapuestas);
        estado.putBoolean ("ABRIRAJUSTES", abrirajustes);
        estado.putString ("DEPORTE2", deporte);
      //  estado.putString ("NOMBRE", nombre);
    }

    @Override
    protected void onRestoreInstanceState(Bundle estado) {
        super.onRestoreInstanceState (estado);
        abrirapuestas = estado.getBoolean("ABRIRAPUESTAS");
        abrirajustes= estado.getBoolean("ABRIRAJUSTES");
        deporte = estado.getString("DEPORTE2");

    }
}
