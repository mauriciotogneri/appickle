package com.mauriciotogneri.appickle.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.fields.TimeField;
import com.mauriciotogneri.appickle.pickers.PickerSelector;

import org.joda.time.DateTime;

public class TimeFieldWidget extends SurveyFieldWidget
{
    private final TimeField timeField;
    private TextView input;

    public TimeFieldWidget(TimeField timeField, ScrollView containerScrollView)
    {
        super(timeField, containerScrollView);

        this.timeField = timeField;
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
                selector.onPickTime(timeField, timeField.id());
            }
        });

        if (timeField.setAsNow())
        {
            setTime(DateTime.now());
        }
    }

    public void setTime(DateTime dateTime)
    {
        timeField.setTime(dateTime);
        input.setText(TimeField.timeFormatter.print(dateTime));
    }
}