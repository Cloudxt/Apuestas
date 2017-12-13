package com.diurnohlc.apuestas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    final static int codigo_registro=0;
    final static int codigo_ajustes=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btregistro = (Button) findViewById(R.id.botonregistro);
        Button btapuestas = (Button) findViewById(R.id.botonapuestas);
        Button btajustes = (Button) findViewById(R.id.botonajustes);
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

        btapuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirApuestas();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menuprincipal,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id= item.getItemId();

        if(id==R.id.menuayuda){
            Intent intent = new Intent(this, Ayuda.class);
            startActivity(intent);
            return true;
        }else{
            Intent intent = new Intent(this, Acercade.class);
            startActivity(intent);
            return true;
        }


    }
    public void abrirRegistro() {

        Intent intent = new Intent(this, Registro.class);
        startActivityForResult(intent,codigo_registro);

    }

    public void abrirApuestas() {

        Intent intent = new Intent(this, Apuestas.class);
        startActivity(intent);

    }
    public void abrirAjustes() {

        Intent intent = new Intent(this, Ajustes.class);
        startActivity(intent);

    }
    @Override
    protected void onActivityResult( int requestCode, int resultCode,Intent data) {
        if (requestCode == codigo_registro && resultCode == RESULT_OK) {


        }
    }

}
