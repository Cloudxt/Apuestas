package com.diurnohlc.apuestas;

/**
 * Created by cloud on 13/12/2017.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

public class DialogoDatePicker extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    public static DialogoDatePicker newInstance(DatePickerDialog.OnDateSetListener listener) {
        DialogoDatePicker dialogo = new DialogoDatePicker();
        dialogo.setListener(listener);
        return dialogo;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendario= Calendar.getInstance();
        return new DatePickerDialog(getActivity(),listener, calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH));
    }
}
