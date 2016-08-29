package com.mauriciotogneri.appickle.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.fields.DateField;
import com.mauriciotogneri.appickle.pickers.PickerSelector;

import org.joda.time.DateTime;

import java.text.DateFormat;

public class DateFieldWidget extends SurveyFieldWidget
{
    private final DateField dateField;
    private TextView input;

    public DateFieldWidget(DateField dateField)
    {
        super(dateField);

        this.dateField = dateField;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup parent, final PickerSelector selector)
    {
        View view = inflate(inflater, parent, R.layout.view_field_date_time);

        input = (TextView) view.findViewById(R.id.field_dateTime);
        input.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                selector.onPickDate(dateField, dateField.id());
            }
        });

        setDate(DateTime.now(), view.getContext());
    }

    public void setDate(DateTime dateTime, Context context)
    {
        dateField.setDate(dateTime);

        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        input.setText(dateFormat.format(dateTime.toDate()));
    }
}