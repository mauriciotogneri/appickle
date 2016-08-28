package com.mauriciotogneri.appickle.model.fields;

import com.mauriciotogneri.appickle.json.JsonCodec.SurveyFieldAdapter.FieldType;
import com.mauriciotogneri.appickle.json.JsonCodec.SurveyFieldAdapter.Format;

public class TextField extends SurveyField
{
    private final Format format;
    private final String placeholder;
    private final String defaultValue;

    public TextField(String id,
                     String description,
                     String error,
                     Boolean required,
                     String result,
                     Format format,
                     String placeholder,
                     String defaultValue)
    {
        super(FieldType.text, id, description, error, required, result);

        this.format = format;
        this.placeholder = placeholder;
        this.defaultValue = defaultValue;
    }

    public Format format()
    {
        return format;
    }

    public String placeholder()
    {
        return placeholder;
    }

    public String defaultValue()
    {
        return defaultValue;
    }
}