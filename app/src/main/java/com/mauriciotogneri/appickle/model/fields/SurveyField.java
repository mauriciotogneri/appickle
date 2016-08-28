package com.mauriciotogneri.appickle.model.fields;

import com.mauriciotogneri.appickle.json.JsonCodec.SurveyFieldAdapter.FieldType;

public abstract class SurveyField
{
    private final FieldType type;
    private final String id;
    private final String description;
    private final String error;
    private final Boolean required;
    private String result;

    protected SurveyField(FieldType type, String id, String description, String error, Boolean required, String result)
    {
        this.type = type;
        this.id = id;
        this.description = description;
        this.error = error;
        this.required = required;
        this.result = result;
    }

    public String id()
    {
        return id;
    }

    public String description()
    {
        return description;
    }

    public String error()
    {
        return error;
    }

    public Boolean required()
    {
        return required;
    }

    public String result()
    {
        return result;
    }

    public void result(String result)
    {
        this.result = result;
    }
}