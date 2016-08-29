package com.mauriciotogneri.appickle.pickers;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.fields.TimeField;

import org.joda.time.DateTime;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener
{
    private OnTimeSelected listener;
    private Boolean canceled = false;

    private static final String PARAMETER_TIME = "time";
    private static final String PARAMETER_ID = "id";

    public static TimePickerFragment create(DateTime dateTime, String id)
    {
        TimePickerFragment fragment = new TimePickerFragment();

        Bundle args = new Bundle();
        args.putString(PARAMETER_TIME, TimeField.timeFormatter.print(dateTime));
        args.putString(PARAMETER_ID, id);
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        DateTime today = TimeField.timeFormatter.parseDateTime(parameter(PARAMETER_TIME));

        TimePickerDialog dialog = new TimePickerDialog(getActivity(), this, today.getHourOfDay(), today.getMinuteOfHour(), DateFormat.is24HourFormat(getActivity()));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.dialog_cancel), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                if (which == DialogInterface.BUTTON_NEGATIVE)
                {
                    canceled = true;
                }
            }
        });

        return dialog;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
    {
        if (!canceled)
        {
            String id = parameter(PARAMETER_ID);
            DateTime dateTime = new DateTime().withHourOfDay(hourOfDay).withMinuteOfHour(minute);

            listener.onTimeSelected(dateTime, id);
        }
    }

    private String parameter(String key)
    {
        Bundle bundle = getArguments();

        return bundle.getString(key);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
            listener = (OnTimeSelected) context;
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public interface OnTimeSelected
    {
        void onTimeSelected(DateTime dateTime, String id);
    }
}