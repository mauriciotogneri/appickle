package com.mauriciotogneri.appickle.model.fields;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.json.JsonSurveyField;
import com.mauriciotogneri.appickle.json.JsonSurveyField.Format;
import com.mauriciotogneri.appickle.json.JsonSurveyField.Type;
import com.mauriciotogneri.appickle.pickers.PickerSelector;

public class StandardField extends SurveyField
{
    private EditText input;
    private final Format format;
    private final String placeholder;
    private final String defaultValue;

    public StandardField(String id,
                         String description,
                         String error,
                         Boolean required,
                         String result,
                         Format format,
                         String placeholder,
                         String defaultValue)
    {
        super(Type.standard, id, description, error, required, result);

        this.format = format;
        this.placeholder = placeholder;
        this.defaultValue = defaultValue;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup parent, PickerSelector selector)
    {
        View view = inflate(inflater, parent, R.layout.view_field_standard);

        this.input = (EditText) view.findViewById(R.id.field_standard_input);

        if (!TextUtils.isEmpty(defaultValue))
        {
            input.setText(defaultValue);
        }

        if (format != null)
        {
            input.setInputType(format.value());
        }

        TextInputLayout inputLayout = (TextInputLayout) view.findViewById(R.id.field_standard_layout);

        if (!TextUtils.isEmpty(placeholder))
        {
            inputLayout.setHint(placeholder);
        }
    }

    @Override
    public boolean isFilled()
    {
        return !TextUtils.isEmpty(result());
    }

    @Override
    public String result()
    {
        return input.getText().toString();
    }

    @Override
    public JsonSurveyField json()
    {
        return new JsonSurveyField(id, type, description, error, required, format, placeholder, defaultValue, null, null, result);
    }
}