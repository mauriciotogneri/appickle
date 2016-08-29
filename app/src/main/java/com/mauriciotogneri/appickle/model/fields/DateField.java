package com.mauriciotogneri.appickle.model.fields;

import com.mauriciotogneri.appickle.json.JsonCodec.SurveyFieldAdapter.FieldType;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateField extends SurveyField
{
    public static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public DateField(String id,
                     String description,
                     String error,
                     Boolean required,
                     String result)
    {
        super(FieldType.date, id, description, error, required, result);
    }

    public void setDate(DateTime dateTime)
    {
        result(dateFormatter.print(dateTime));
    }

    public DateTime dateTime()
    {
        return dateFormatter.parseDateTime(result());
    }
}