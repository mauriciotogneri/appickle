package com.mauriciotogneri.appickle.model;

public class Survey
{
    private final String description;
    private final SurveyField[] fields;

    public Survey(String description, SurveyField[] fields)
    {
        this.description = description;
        this.fields = fields;
    }

    public String description()
    {
        return description;
    }

    public SurveyField[] fields()
    {
        return fields;
    }
}