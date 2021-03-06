package com.diurnohlc.apuestas;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.res.Resources;
        import android.os.Bundle;
        import android.preference.PreferenceManager;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.Spinner;
        import android.widget.TabHost;
        import android.widget.Toast;

/**
 * Created by cloud on 13/12/2017.
 */


public class Apuestas extends AppCompatActivity {
    Button btaceptar,btvolver;
    CheckBox cbfutbol,cbtenis,cbbalonmano,cbbaloncesto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apuestas);
        btaceptar = (Button) findViewById(R.id.botonaceptar5);
        btvolver = (Button) findViewById(R.id.botonvolver5);
        cbfutbol = (CheckBox) findViewById(R.id.checkBoxfutbol);
        cbtenis = (CheckBox) findViewById(R.id.checkBoxtenis);
        cbbaloncesto = (CheckBox) findViewById(R.id.checkBoxbaloncesto);
        cbbalonmano = (CheckBox) findViewById(R.id.checkBoxbalonmano);
        btaceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                aceptar();
            }
        });
        btvolver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        PreferenceManager.setDefaultValues(this,R.xml.settings,true);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String preferenciaDeporte = sharedPref.getString("deporte","tenis" );
        preferencias(preferenciaDeporte);

    }
    public void aceptar(){
        int contador=0;
        String deporte=null;
        Intent intent=new Intent();
        if(cbfutbol.isChecked()){
            contador++;
            deporte="futbol";
        } if(cbbaloncesto.isChecked()){
           contador++;
            deporte="baloncesto";
        } if(cbbalonmano.isChecked()){
            deporte="balonmano";
            contador++;
        } if(cbtenis.isChecked()){
            contador++;
            deporte="tenis";

        }if(contador==1){
            Toast.makeText(getApplicationContext(),R.string.suerte,
                    Toast.LENGTH_SHORT).show();
            intent.putExtra("OBTENERDEPORTE",deporte);
            setResult(RESULT_OK,intent);
            finish();
        }else if(contador>1){
            Toast.makeText(getApplicationContext(),R.string.vcompleta,
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),R.string.una,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void preferencias(String deporte){
        if(deporte.equals("tenis")){
            cbtenis.setChecked(true);
        }
        if(deporte.equals("futbol")){
            cbfutbol.setChecked(true);
        }
        if(deporte.equals("baloncesto")){
            cbbaloncesto.setChecked(true);
        }if(deporte.equals("balonmano")){
            cbbalonmano.setChecked(true);
        }
    }
}

