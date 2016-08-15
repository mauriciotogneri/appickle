package com.mauriciotogneri.appickle.json;

import com.mauriciotogneri.appickle.model.fields.CheckboxField;
import com.mauriciotogneri.appickle.model.fields.RadioField;
import com.mauriciotogneri.appickle.model.fields.StandardField;
import com.mauriciotogneri.appickle.model.fields.SurveyField;

import java.util.List;

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

    // radio|select|checkbox
    private final List<JsonFieldValue> values;

    // result
    private final String result;

    public enum Type
    {
        standard,
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

    public JsonSurveyField(String id, Type type, String description, Boolean required, Format format, String placeholder, String defaultValue, List<JsonFieldValue> values, String result)
    {
        this.id = id;
        this.type = type;
        this.description = description;
        this.required = required;
        this.format = format;
        this.placeholder = placeholder;
        this.defaultValue = defaultValue;
        this.values = values;
        this.result = result;
    }

    @Override
    public SurveyField model()
    {
        switch (type)
        {
            case standard:
                return new StandardField(id, description, required, result, format, placeholder, defaultValue);

            case radio:
            case select:
                return new RadioField(type, id, description, required, result, fromList(values));

            case checkbox:
                return new CheckboxField(type, id, description, required, result, fromList(values));

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