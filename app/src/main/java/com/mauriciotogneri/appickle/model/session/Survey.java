package com.mauriciotogneri.appickle.model.session;

import com.mauriciotogneri.appickle.json.JsonSurvey;
import com.mauriciotogneri.appickle.model.ModelEntity;
import com.mauriciotogneri.appickle.model.fields.SurveyField;

import java.util.List;

public class Survey extends ModelEntity<JsonSurvey>
{
    private final String description;
    private final List<SurveyField> fields;

    public Survey(String description, List<SurveyField> fields)
    {
        this.description = description;
        this.fields = fields;
    }

    public String description()
    {
        return description;
    }

    public List<SurveyField> fields()
    {
        return fields;
    }

    @Override
    public JsonSurvey json()
    {
        return new JsonSurvey(description, fromList(fields));
    }
}