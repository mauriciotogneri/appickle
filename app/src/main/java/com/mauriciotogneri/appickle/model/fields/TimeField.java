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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeField extends SurveyField
{
    private TextView input;

    private static final DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HH:mm");

    public TimeField(String id,
                     String description,
                     String error,
                     Boolean required,
                     String result)
    {
        super(Type.time, id, description, error, required, result);
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup parent, final PickerSelector selector)
    {
        View view = inflate(inflater, parent, R.layout.view_field_date_time);

        this.input = (TextView) view.findViewById(R.id.field_dateTime);
        this.input.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                selector.onPickTime(TimeField.this);
            }
        });
        setTime(DateTime.now());
    }

    public void setTime(DateTime dateTime)
    {
        this.input.setText(timeFormatter.print(dateTime));
    }

    @Override
    public boolean isFilled()
    {
        return !TextUtils.isEmpty(result());
    }

    @Override
    public String result()
    {
        return input.getText().toString();
    }

    @Override
    public JsonSurveyField json()
    {
        return new JsonSurveyField(id, type, description, error, required, null, null, null, null, null, result);
    }
}