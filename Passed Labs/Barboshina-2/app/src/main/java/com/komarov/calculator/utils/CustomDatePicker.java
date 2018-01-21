package com.komarov.calculator.utils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.komarov.calculator.R;

import java.util.Calendar;

/**
 * Created by Ilia on 13.10.2017.
 */

public class CustomDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    public String DateText;
    private TextView TextView;

    public CustomDatePicker() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        Dialog picker = new DatePickerDialog(getActivity(), this, year, month, day);
        picker.setTitle(getResources().getString(R.string.datepicker_choose_date));
        return picker;
    }

    @Override
    public void onStart() {
        super.onStart();
        Button nButton = ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE);
        nButton.setText(getResources().getString(R.string.datepicker_ready));
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        int mn = month + 1;
        DateText = day + "." + mn + "." + year;
        TextView.setText(day + "." + mn + "." + year);
    }
}
