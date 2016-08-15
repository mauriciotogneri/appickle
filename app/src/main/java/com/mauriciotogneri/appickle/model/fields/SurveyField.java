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
    protected final Boolean required;
    protected String result;

    protected SurveyField(Type type, String id, String description, Boolean required, String result)
    {
        this.type = type;
        this.id = id;
        this.description = description;
        this.required = required;
        this.result = result;
    }

    public abstract void init(LayoutInflater inflater, ViewGroup parent);

    protected abstract boolean isFilled();

    protected abstract String result();

    protected View inflate(LayoutInflater inflater, ViewGroup parent, int resourceId)
    {
        View view = inflater.inflate(resourceId, parent, false);
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

        return view;
    }

    public boolean validate()
    {
        if (isFilled())
        {
            this.result = result();

            return true;
        }
        else
        {
            return !required;
        }
    }
}