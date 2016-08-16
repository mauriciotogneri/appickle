package com.mauriciotogneri.appickle.model.fields;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.json.JsonSurveyField;
import com.mauriciotogneri.appickle.json.JsonSurveyField.Type;
import com.mauriciotogneri.appickle.model.ModelEntity;

public abstract class SurveyField extends ModelEntity<JsonSurveyField>
{
    protected final Type type;
    protected final String id;
    protected final String description;
    protected final String error;
    protected final Boolean required;
    protected String result;
    private TextView errorLabel;

    protected SurveyField(Type type, String id, String description, String error, Boolean required, String result)
    {
        this.type = type;
        this.id = id;
        this.description = description;
        this.error = error;
        this.required = required;
        this.result = result;
    }

    public abstract void init(LayoutInflater inflater, ViewGroup parent);

    protected abstract boolean isFilled();

    protected abstract String result();

    protected View inflate(LayoutInflater inflater, ViewGroup parent, int resourceId)
    {
        View view = inflater.inflate(R.layout.field_base, parent, false);
        parent.addView(view);

        ViewGroup container = (ViewGroup) view.findViewById(R.id.field_base_container);
        container.addView(inflater.inflate(resourceId, container, false));

        TextView descriptionLabel = (TextView) view.findViewById(R.id.field_description);

        if (!TextUtils.isEmpty(description))
        {
            descriptionLabel.setText(description);
        }
        else
        {
            descriptionLabel.setVisibility(View.GONE);
        }

        errorLabel = (TextView) view.findViewById(R.id.field_error);

        if (!TextUtils.isEmpty(error))
        {
            errorLabel.setText(error);
        }

        return view;
    }

    public boolean validate()
    {
        boolean valid;

        if (isFilled())
        {
            this.result = result();

            valid = true;
        }
        else
        {
            valid = !required;
        }

        if (valid)
        {
            errorLabel.setVisibility(View.GONE);
        }
        else
        {
            if (!TextUtils.isEmpty(error))
            {
                errorLabel.setVisibility(View.VISIBLE);
            }
        }

        return valid;
    }
}