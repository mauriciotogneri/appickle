package com.mauriciotogneri.appickle.json;

import com.mauriciotogneri.appickle.model.session.Survey;

import java.util.List;

public class JsonSurvey extends JsonEntity<Survey>
{
    private final String description;
    private final List<JsonSurveyField> fields;

    public JsonSurvey(String description, List<JsonSurveyField> fields)
    {
        this.description = description;
        this.fields = fields;
    }

    @Override
    public Survey model()
    {
        return new Survey(description, fromList(fields));
    }
}