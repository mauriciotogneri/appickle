package com.mauriciotogneri.appickle.model.fields;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.json.JsonSurveyField;
import com.mauriciotogneri.appickle.json.JsonSurveyField.Format;
import com.mauriciotogneri.appickle.json.JsonSurveyField.Type;

public class StandardField extends SurveyField
{
    private View view;
    private final Format format;
    private final String placeholder;
    private final String defaultValue;

    public StandardField(String id,
                         String description,
                         Boolean required,
                         String value,
                         Format format,
                         String placeholder,
                         String defaultValue)
    {
        super(id, description, required, value);

        this.format = format;
        this.placeholder = placeholder;
        this.defaultValue = defaultValue;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup parent)
    {
        view = inflater.inflate(R.layout.field_standard, parent, false);
        parent.addView(view);

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
            input.setInputType(format.value());
        }

        TextInputLayout inputLayout = (TextInputLayout) view.findViewById(R.id.field_standard_inputLayout);

        if (!TextUtils.isEmpty(placeholder))
        {
            inputLayout.setHint(placeholder);
        }
    }

    @Override
    public boolean isFilled()
    {
        return !TextUtils.isEmpty(value());
    }

    @Override
    public String value()
    {
        EditText input = (EditText) view.findViewById(R.id.field_standard_input);

        return input.getText().toString();
    }

    @Override
    public JsonSurveyField json()
    {
        return new JsonSurveyField(id, Type.standard, description, required, format, placeholder, defaultValue, value);
    }
}