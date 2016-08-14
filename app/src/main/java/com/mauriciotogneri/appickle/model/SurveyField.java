package com.mauriciotogneri.appickle.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.fields.StandardField;

public class SurveyField
{
    // all
    private String id;
    private Type type;
    private String description;
    private Boolean required;

    // standard
    private Format format;
    private String placeholder;
    private String defaultValue;

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

    public void view(LayoutInflater inflater, ViewGroup parent)
    {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.field_standard, parent, false);
        parent.addView(view);

        FieldInitializer fieldInitializer = fieldInitializer();
        fieldInitializer.init(view, this);
    }

    private FieldInitializer fieldInitializer()
    {
        switch (type)
        {
            case standard:
                return new StandardField(description, format, placeholder, defaultValue);

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

    public interface FieldInitializer
    {
        void init(ViewGroup view, SurveyField field);
    }
}