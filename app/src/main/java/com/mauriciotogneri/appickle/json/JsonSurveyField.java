package com.mauriciotogneri.appickle.json;

import com.mauriciotogneri.appickle.model.fields.StandardField;
import com.mauriciotogneri.appickle.model.fields.SurveyField;

public class JsonSurveyField extends JsonEntity<SurveyField>
{
    // all
    private final String id;
    private final Type type;
    private final String description;
    private final Boolean required;

    // standard
    private final Format format;
    private final String placeholder;
    private final String defaultValue;

    // result
    private final String value;

    public enum Type
    {
        standard,
        text,
        radio,
        select,
        checkbox,
        toggle,
        range,
        date,
        time
    }

    public enum Format
    {
        text(0x00000001 | 0x000000b1),
        textMultiLine(0x00020001 | 0x000000b1),
        textAutoComplete(0x00010001),
        integer(0x00000002),
        decimal(0x00002002),
        telephone(0x00000003),
        email(0x00000021),
        uri(0x00000011);

        private final int value;

        Format(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return value;
        }
    }

    public JsonSurveyField(String id, Type type, String description, Boolean required, Format format, String placeholder, String defaultValue, String value)
    {
        this.id = id;
        this.type = type;
        this.description = description;
        this.required = required;
        this.format = format;
        this.placeholder = placeholder;
        this.defaultValue = defaultValue;
        this.value = value;
    }

    @Override
    public SurveyField model()
    {
        switch (type)
        {
            case standard:
                return new StandardField(id, description, required, value, format, placeholder, defaultValue);

            case text:
                break;
            case radio:
                break;
            case select:
                break;
            case checkbox:
                break;
            case toggle:
                break;
            case range:
                break;
            case date:
                break;
            case time:
                break;
        }

        throw new RuntimeException();
    }
}