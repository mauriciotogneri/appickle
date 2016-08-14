package com.mauriciotogneri.appickle.model;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;

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
        text(0x00000001),
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
    }

    public void view(LayoutInflater inflater, ViewGroup parent)
    {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.field_standard, parent, false);
        parent.addView(view);

        switch (type)
        {
            case standard:
                initStandardField(view);

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
    }

    private void initStandardField(ViewGroup view)
    {
        TextView descriptionLabel = (TextView) view.findViewById(R.id.field_standard_description);

        if (!TextUtils.isEmpty(description))
        {
            descriptionLabel.setText(description);
        }
        else
        {
            descriptionLabel.setVisibility(View.GONE);
        }

        EditText input = (EditText) view.findViewById(R.id.field_standard_input);

        if (!TextUtils.isEmpty(defaultValue))
        {
            input.setText(defaultValue);
        }

        if (format != null)
        {
            input.setInputType(format.value);
        }

        TextInputLayout inputLayout = (TextInputLayout) view.findViewById(R.id.field_standard_inputLayout);

        if (!TextUtils.isEmpty(placeholder))
        {
            inputLayout.setHint(placeholder);
        }
    }
}