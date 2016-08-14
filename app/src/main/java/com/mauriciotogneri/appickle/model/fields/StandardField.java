package com.mauriciotogneri.appickle.model.fields;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.SurveyField;
import com.mauriciotogneri.appickle.model.SurveyField.FieldInitializer;
import com.mauriciotogneri.appickle.model.SurveyField.Format;

public class StandardField implements FieldInitializer
{
    private final String description;
    private final Format format;
    private final String placeholder;
    private final String defaultValue;

    public StandardField(String description, Format format, String placeholder, String defaultValue)
    {
        this.description = description;
        this.format = format;
        this.placeholder = placeholder;
        this.defaultValue = defaultValue;
    }

    @Override
    public void init(ViewGroup view, SurveyField field)
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
            input.setInputType(format.value());
        }

        TextInputLayout inputLayout = (TextInputLayout) view.findViewById(R.id.field_standard_inputLayout);

        if (!TextUtils.isEmpty(placeholder))
        {
            inputLayout.setHint(placeholder);
        }
    }
}