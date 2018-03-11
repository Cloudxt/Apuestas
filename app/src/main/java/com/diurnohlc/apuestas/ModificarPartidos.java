package com.diurnohlc.apuestas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by cloud on 11/03/2018.
 */

public class ModificarPartidos extends AppCompatActivity {
    MiBaseDedatosHelper bdHelp;
    SQLiteDatabase bd;
    Cursor cur;
    Button modificar;
    String idpartido,tipoDeporte, equipo1 ,equipo2 ,resultado1 ,resultado2;
    EditText etIdPartido,ettipoDeporte,etEquipo1,etEquipo2,etResultado1,etResultado2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadirdeporte);
        modificar=(Button)findViewById(R.id.anadirpartido);
        modificar.setText(R.string.modificarpartido);
        etIdPartido=findViewById(R.id.etIdMatch);
        ettipoDeporte=findViewById(R.id.etDeporte);
        etEquipo1=findViewById(R.id.etEquipo1);
        etEquipo2=findViewById(R.id.etEquipo2);
        etResultado1=findViewById(R.id.etResult1);
        etResultado2=findViewById(R.id.etResult2);

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarPartido();
            }
        });
        bdHelp= new MiBaseDedatosHelper(this,"partidos",null,1);


    }

    private void modificarPartido() {
        if(comprobarCampos()){
            if(!comprobarIdPartido()){
                Toast.makeText(getApplicationContext(), R.string.noModificar,
                        Toast.LENGTH_SHORT).show();
            }else{
                modificarPartidoEnLaBD();


            }


        }

    }

    private void modificarPartidoEnLaBD() {
        bd=bdHelp.getWritableDatabase();
        ContentValues registro= new ContentValues();
        //registro.put("idPartido",Integer.parseInt(etIdPartido.getText().toString()));
        registro.put("tipoDeporte",ettipoDeporte.getText().toString());
        registro.put("equipo1",etEquipo1.getText().toString());
        registro.put("equipo2",etEquipo2.getText().toString());
        registro.put("resultado1",Integer.parseInt(etResultado1.getText().toString()));
        registro.put("resultado2",Integer.parseInt(etResultado2.getText().toString()));
        bd.update("partidos",registro,"idPartido="+etIdPartido.getText().toString(),null);
        Toast.makeText(getApplicationContext(), R.string.partidoModificado,
                Toast.LENGTH_SHORT).show();



    }

    private boolean comprobarIdPartido() {
        boolean comprobar=false;
        bd = bdHelp.getReadableDatabase();
        cur = bd.rawQuery("SELECT idPartido FROM partidos", null);
        int comprobarId=Integer.parseInt(etIdPartido.getText().toString());
        if(bd.isOpen()){
            if(cur.moveToNext()){
                do {
                    if (comprobarId==cur.getInt(0))
                        comprobar=true;


                }while (cur.moveToNext()&&!comprobar);
            }

        }
        bd.close();
        return comprobar;
    }

    private boolean comprobarCampos() {
        if (etIdPartido.getText().toString().equals("")||Integer.parseInt(etIdPartido.getText().toString())<0){
            Toast.makeText(getApplicationContext(), R.string.noIdPartido,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(ettipoDeporte.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), R.string.noTipoDeporte,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etEquipo1.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), R.string.noEquipo,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etEquipo2.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), R.string.noEquipo,
                    Toast.LENGTH_SHORT).show();

            return false;
        }
        if (etResultado1.getText().toString().equals("")||Integer.parseInt(etResultado1.getText().toString())<0
                ||Integer.parseInt(etResultado1.getText().toString())>300){
            Toast.makeText(getApplicationContext(), R.string.noResultado,
                    Toast.LENGTH_SHORT).show();

            return false;
        }
        if (etResultado2.getText().toString().equals("")||Integer.parseInt(etResultado2.getText().toString())<0
                ||Integer.parseInt(etResultado2.getText().toString())>300){
            Toast.makeText(getApplicationContext(), R.string.noResultado,
                    Toast.LENGTH_SHORT).show();

            return false;
        }
        return true;
    }
}
