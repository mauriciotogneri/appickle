package com.mauriciotogneri.appickle.json;

import com.mauriciotogneri.appickle.model.fields.CheckboxField;
import com.mauriciotogneri.appickle.model.fields.DropdownField;
import com.mauriciotogneri.appickle.model.fields.RadioField;
import com.mauriciotogneri.appickle.model.fields.DateField;
import com.mauriciotogneri.appickle.model.fields.StandardField;
import com.mauriciotogneri.appickle.model.fields.SurveyField;
import com.mauriciotogneri.appickle.model.fields.TimeField;
import com.mauriciotogneri.appickle.model.fields.ToggleField;

import java.util.List;

public class JsonSurveyField extends JsonEntity<SurveyField>
{
    // all
    private final String id;
    private final Type type;
    private final String description;
    private final String error;
    private final Boolean required;

    // standard
    private final Format format;
    private final String placeholder;
    private final String defaultValue;

    // radio|select|checkbox
    private final List<JsonFieldValue> values;

    // toggle
    private final Boolean selected;

    // result
    private final String result;

    public enum Type
    {
        standard,
        radio,
        dropdown,
        checkbox,
        toggle,
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

    public JsonSurveyField(String id, Type type, String description, String error, Boolean required, Format format, String placeholder, String defaultValue, List<JsonFieldValue> values, Boolean selected, String result)
    {
        this.id = id;
        this.type = type;
        this.description = description;
        this.error = error;
        this.required = required;
        this.format = format;
        this.placeholder = placeholder;
        this.defaultValue = defaultValue;
        this.values = values;
        this.selected = selected;
        this.result = result;
    }

    @Override
    public SurveyField model()
    {
        switch (type)
        {
            case standard:
                return new StandardField(id, description, error, required, result, format, placeholder, defaultValue);

            case radio:
                return new RadioField(type, id, description, error, required, result, fromList(values));

            case dropdown:
                return new DropdownField(type, id, description, error, required, result, fromList(values));

            case checkbox:
                return new CheckboxField(type, id, description, error, required, result, fromList(values));

            case toggle:
                return new ToggleField(id, description, error, required, result, selected);

            case date:
                return new DateField(id, description, error, required, result);

            case time:
                return new TimeField(id, description, error, required, result);
        }

        throw new RuntimeException();
    }
}