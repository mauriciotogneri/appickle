package com.mauriciotogneri.appickle.pickers;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import org.joda.time.DateTime;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener
{
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        DateTime today = DateTime.now();

        return new TimePickerDialog(getActivity(), this, today.getHourOfDay(), today.getMinuteOfHour(), DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
    {
    }
}