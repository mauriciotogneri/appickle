package com.mauriciotogneri.appickle.model.fields;

import com.mauriciotogneri.appickle.json.JsonCodec.SurveyFieldAdapter.FieldType;

import java.util.List;

public class CheckboxField extends SurveyField
{
    private final List<FieldValue> values;

    public CheckboxField(FieldType type,
                         String id,
                         String description,
                         String error,
                         Boolean required,
                         String result,
                         List<FieldValue> values)
    {
        super(type, id, description, error, required, result);

        this.values = values;
    }

    public List<FieldValue> values()
    {
        return values;
    }
}