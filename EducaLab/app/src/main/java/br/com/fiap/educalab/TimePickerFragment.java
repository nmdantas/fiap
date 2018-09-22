package br.com.fiap.educalab;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;

import java.util.Calendar;


public class TimePickerFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(),(TimePickerDialog.OnTimeSetListener) getActivity(),
                hour,minute,android.text.format.DateFormat.is24HourFormat(getActivity()));
    }
}