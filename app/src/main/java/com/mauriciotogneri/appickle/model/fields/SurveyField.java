package com.mauriciotogneri.appickle.model.fields;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mauriciotogneri.appickle.json.JsonSurveyField;
import com.mauriciotogneri.appickle.model.ModelEntity;

public abstract class SurveyField extends ModelEntity<JsonSurveyField>
{
    protected final String id;
    protected final String description;
    protected final Boolean required;
    protected String value;

    protected SurveyField(String id, String description, Boolean required, String value)
    {
        this.id = id;
        this.description = description;
        this.required = required;
        this.value = value;
    }

    public abstract void init(LayoutInflater inflater, ViewGroup parent);

    protected abstract boolean isFilled();

    protected abstract String value();

    public boolean validate()
    {
        if (isFilled())
        {
            this.value = value();

            return true;
        }
        else
        {
            return !required;
        }
    }
}