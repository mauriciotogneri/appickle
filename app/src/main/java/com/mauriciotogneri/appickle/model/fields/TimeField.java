package com.mauriciotogneri.appickle.model.fields;

import com.mauriciotogneri.appickle.json.JsonCodec.SurveyFieldAdapter.FieldType;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeField extends SurveyField
{
    public static final DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HH:mm");

    public TimeField(String id,
                     String description,
                     String error,
                     Boolean required,
                     String result)
    {
        super(FieldType.time, id, description, error, required, result);
    }

    public void setTime(DateTime dateTime)
    {
        result(timeFormatter.print(dateTime));
    }

    public DateTime time()
    {
        return timeFormatter.parseDateTime(result());
    }
}