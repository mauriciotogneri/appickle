package com.mauriciotogneri.appickle.pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.fields.DateField;

import org.joda.time.DateTime;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    private OnDateSelected listener;
    private Boolean canceled = false;

    private static final String PARAMETER_DATE = "date";
    private static final String PARAMETER_ID = "id";

    public static DatePickerFragment create(DateTime dateTime, String id)
    {
        DatePickerFragment fragment = new DatePickerFragment();

        Bundle args = new Bundle();
        args.putString(PARAMETER_DATE, DateField.dateFormatter.print(dateTime));
        args.putString(PARAMETER_ID, id);
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        DateTime today = DateField.dateFormatter.parseDateTime(parameter(PARAMETER_DATE));

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, today.getYear(), today.getMonthOfYear() - 1, today.getDayOfMonth());
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

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day)
    {
        if (!canceled)
        {
            String id = parameter(PARAMETER_ID);
            DateTime dateTime = new DateTime().withYear(year).withMonthOfYear(month + 1).withDayOfMonth(day);

            listener.onDateSelected(dateTime, id);
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
            listener = (OnDateSelected) context;
        }
        catch (Exception e)
        {
            // ignore
        }
    }

    public interface OnDateSelected
    {
        void onDateSelected(DateTime dateTime, String id);
    }
}