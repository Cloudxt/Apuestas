package com.diurnohlc.apuestas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cloud on 10/03/2018.
 */

public class MiBaseDedatosHelper extends SQLiteOpenHelper {


    public MiBaseDedatosHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version ){
    super(context,nombre,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE partidos (idPartido INTEGER,tipoDeporte TEXT, " +
                "equipo1 TEXT, equipo2 TEXT,resultado1 INTEGER, resultado2 INTEGER)");
        db.execSQL("INSERT INTO partidos (idPartido, tipoDeporte, equipo1, equipo2, resultado1,resultado2)" +
                " VALUES (1, 'tennis','Almeria','Granada',20,30 )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS partidos");
//Se crea la nueva versión de la tabla
        db.execSQL("CREATE TABLE partidos (idPartido INTEGER,tipoDeporte TEXT, " +
                "equipo1 TEXT, equipo2 TEXT,resultado1 INTEGER, resultado2 INTEGER)");
    }
}
