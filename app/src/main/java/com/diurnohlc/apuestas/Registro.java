package com.diurnohlc.apuestas;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by cloud on 13/12/2017.
 */
public class Registro extends AppCompatActivity {
    Button btvalidar, btvolver;
    EditText tfecha, nombre, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        btvalidar = (Button) findViewById(R.id.botonvalidar);
        btvolver = (Button) findViewById(R.id.botonvolver3);
        tfecha = (EditText) findViewById(R.id.textfecha);
        nombre = (EditText) findViewById(R.id.textnombre);
        email = (EditText) findViewById(R.id.textemail);

        btvalidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });
        btvolver.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                finish();
            }
        });
        tfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDatePicker();
            }
        });
    }

    public void validar(){
        Intent intent=new Intent();
        if(!comprobar()){

        }else{
            Toast.makeText(getApplicationContext(),R.string.guardado,
                    Toast.LENGTH_SHORT).show();
            intent.putExtra("registro",true);
            setResult(RESULT_OK,intent);
            finish();
        }


    }
    public boolean comprobar() {


        if (nombre.getText().equals("")){
            Toast.makeText(getApplicationContext(),R.string.nonombre,
                    Toast.LENGTH_SHORT).show();
            return false;}
        else if (email.getText().equals("")) {
            Toast.makeText(getApplicationContext(),R.string.noemail,
                    Toast.LENGTH_SHORT).show();
            return false;
        }else if (comprobarEdad()) {
            Toast.makeText(getApplicationContext(),"No eres mayor de edad",
                    Toast.LENGTH_SHORT).show();
            return false;
        }else
            return true;
    }

    private void abrirDatePicker() {

        DialogoDatePicker dialogo = DialogoDatePicker.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anyo, int mes, int dia) {

                final String selectedDate = dia + " / " + (mes + 1) + " / " + anyo;
                tfecha.setText(selectedDate);
            }
        });
        dialogo.show(getFragmentManager(), "datePicker");
    }

    public boolean comprobarEdad() {
        Calendar fechaactual = Calendar.getInstance();
        SimpleDateFormat fechaformato = new SimpleDateFormat("dd / MM / yyyy");
        long edad=0;

        Date fechaseleccionada = null;
        try {
            fechaseleccionada = fechaformato.parse(tfecha.getText().toString());
            edad = (fechaactual.getTimeInMillis() - fechaseleccionada.getTime())/31536000000l;
        } catch (ParseException pe) {

        }
        if (edad<=18)
            return true;
        else
            return false;
    }

}
