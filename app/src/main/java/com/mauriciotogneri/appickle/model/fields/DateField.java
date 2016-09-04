package com.mauriciotogneri.appickle.model.fields;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateField extends SurveyField
{
    private final Boolean setAsNow;

    public static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public DateField(String id,
                     String description,
                     String error,
                     Boolean required,
                     String result,
                     Boolean setAsNow)
    {
        super(id, description, error, required, result);

        this.setAsNow = setAsNow;
    }

    public Boolean setAsNow()
    {
        return setAsNow;
    }

    public void setDate(DateTime dateTime)
    {
        result(dateFormatter.print(dateTime));
    }

    public DateTime dateTime()
    {
        if (!isEmpty())
        {
            return dateFormatter.parseDateTime(result());
        }
        else
        {
            return DateTime.now();
        }
    }
}