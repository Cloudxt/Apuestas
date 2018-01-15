package com.diurnohlc.apuestas;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by cloud on 13/12/2017.
 */
public class Registro extends AppCompatActivity {
    private Control control ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
       control = (Control) findViewById(R.id.control);
       control.setListener(new RegistroListener() {
           @Override
           public void OnLogin(String usuario, String email, String fecha) {
               validar(usuario,email,fecha);
           }
       });
       control.setListenerDate(new DatepickerInterface() {
           @Override
           public void setListener() {
                abrirDatePicker();
           }
       });
       control.setListenerVolver(new VolverInterface() {
           @Override
           public void setListener() {
               finish();
           }
       });
    }

    public void validar(String nombre,String email,String fecha){
        Intent intent = new Intent(this, MainActivity.class);
        if(!comprobar(nombre,email,fecha)){

        }else{
            Toast.makeText(getApplicationContext(),R.string.guardado,
                    Toast.LENGTH_SHORT).show();
            intent.putExtra("registro",true);

            Bundle bundle = new Bundle();
            bundle.putString("NOMBRE",nombre);
            bundle.putString("CORREO",email);
            bundle.putString("FECHA",fecha);

            intent.putExtras(bundle);
            setResult(RESULT_OK,intent);
            finish();
        }


    }
    public boolean comprobar(String nombre,String email, String fecha) {


        if (nombre.equals("")){
            Toast.makeText(getApplicationContext(),R.string.nonombre,
                    Toast.LENGTH_SHORT).show();
            return false;}
        else if (!validarEmail(email)) {
            Toast.makeText(getApplicationContext(),R.string.noemail,
                    Toast.LENGTH_SHORT).show();
            return false;
        }else if (comprobarEdad(fecha)) {
            Toast.makeText(getApplicationContext(),R.string.noedad,
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
                control.setTfecha(selectedDate);
            }
        });
        dialogo.show(getFragmentManager(), "datePicker");
    }

    private boolean validarEmail(String email){
        Pattern validaremail = Patterns.EMAIL_ADDRESS;
        String comprobacion = email;
        return validaremail.matcher(comprobacion).matches();
    }

    public boolean comprobarEdad(String fecha) {
        Calendar fechaactual = Calendar.getInstance();
        SimpleDateFormat fechaformato = new SimpleDateFormat("dd / MM / yyyy");
        long edad=0;

        Date fechaseleccionada = null;
        try {
            fechaseleccionada = fechaformato.parse(fecha);
            edad = (fechaactual.getTimeInMillis() - fechaseleccionada.getTime())/31536000000l;
        } catch (ParseException pe) {

        }
        if (edad<=18)
            return true;
        else
            return false;
    }

    protected void onSaveInstanceState(Bundle estado) {
        super.onSaveInstanceState(estado);
        estado.putString ("NOMBRE", control.getNombre().getText().toString());
        estado.putString ("EMAIL", control.getEmail().getText().toString());
        estado.putString ("FECHA", control.getTfecha().getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(Bundle estado) {
        super.onRestoreInstanceState (estado);
        control.setNombre(estado.getString("NOMBRE"));
        control.setEmail(estado.getString("EMAIL"));
        control.setTfecha(estado.getString("FECHA"));
    }
}
