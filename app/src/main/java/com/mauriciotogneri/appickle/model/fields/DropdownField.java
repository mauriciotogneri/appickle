package com.mauriciotogneri.appickle.model.fields;

import java.util.List;

public class DropdownField extends SurveyField
{
    private final List<FieldValue> values;

    public DropdownField(String id,
                         String description,
                         String error,
                         Boolean required,
                         String result,
                         List<FieldValue> values)
    {
        super(id, description, error, required, result);

        this.values = values;
    }

    public List<FieldValue> values()
    {
        return values;
    }
}