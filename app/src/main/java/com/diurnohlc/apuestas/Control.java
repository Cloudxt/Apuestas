package com.diurnohlc.apuestas;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by cloud on 13/01/2018.
 */


public class Control extends LinearLayout {
    private Button btvalidar, btvolver;
    private EditText tfecha, nombre, email;
    private RegistroListener listener;
    private DatepickerInterface listenerDate;
    private VolverInterface listenerVolver;
    public Control(Context context) {
        super(context);
        iniciar();
    }

    public EditText getTfecha() {
        return tfecha;
    }

    public void setTfecha(String tfecha) {
        this.tfecha.setText(tfecha);
    }

    public EditText getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.setText(nombre);
    }

    public EditText getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public Control(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        iniciar();
    }

    private void iniciar() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.control,this, true);
        btvalidar = (Button) findViewById(R.id.botonvalidar);
        btvolver = (Button) findViewById(R.id.botonvolver3);
        tfecha = (EditText) findViewById(R.id.textfecha);
        nombre = (EditText) findViewById(R.id.textnombre);
        email = (EditText) findViewById(R.id.textemail);
        asignarEventos();
    }
    private void asignarEventos(){
        btvalidar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnLogin(nombre.getText().toString(),email.getText().toString(),tfecha.getText().toString());
            }
        });
        btvolver.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listenerVolver.setListener();
            }
        });
        tfecha.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listenerDate.setListener();
            }
        });
    }


    public void setListener(RegistroListener listener) {
        this.listener = listener;
    }
    public void setListenerDate(DatepickerInterface listenerDate){
        this.listenerDate=listenerDate;
    }

    public void setListenerVolver(VolverInterface listenerVolver) {
        this.listenerVolver = listenerVolver;
    }
}
