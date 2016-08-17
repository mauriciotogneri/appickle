package com.mauriciotogneri.appickle.pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import org.joda.time.DateTime;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        DateTime today = DateTime.now();

        return new DatePickerDialog(getActivity(), this, today.getYear(), today.getMonthOfYear(), today.getDayOfMonth());
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day)
    {
    }
}