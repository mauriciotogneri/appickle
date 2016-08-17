package com.mauriciotogneri.appickle.model.fields;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.json.JsonSurveyField;
import com.mauriciotogneri.appickle.json.JsonSurveyField.Type;
import com.mauriciotogneri.appickle.pickers.PickerSelector;

import org.joda.time.DateTime;

import java.text.DateFormat;

public class DateField extends SurveyField
{
    private TextView input;
    private DateFormat dateFormat;
    private DateTime selectedDate;

    public DateField(String id,
                     String description,
                     String error,
                     Boolean required,
                     String result)
    {
        super(Type.date, id, description, error, required, result);
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup parent, final PickerSelector selector)
    {
        View view = inflate(inflater, parent, R.layout.view_field_date_time);

        dateFormat = android.text.format.DateFormat.getLongDateFormat(inflater.getContext());

        this.input = (TextView) view.findViewById(R.id.field_dateTime);
        this.input.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                selector.onPickDate(DateField.this);
            }
        });
        setDate(DateTime.now());
    }

    public void setDate(DateTime dateTime)
    {
        this.selectedDate = dateTime;
        this.input.setText(dateFormat.format(dateTime.toDate()));
    }

    @Override
    public boolean isFilled()
    {
        return !TextUtils.isEmpty(result());
    }

    @Override
    public String result()
    {
        return (selectedDate != null) ? selectedDate.toString() : null;
    }

    @Override
    public JsonSurveyField json()
    {
        return new JsonSurveyField(id, type, description, error, required, null, null, null, null, null, result);
    }
}