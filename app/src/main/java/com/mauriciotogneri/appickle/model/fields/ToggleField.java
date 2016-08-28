package com.mauriciotogneri.appickle.model.fields;

import com.mauriciotogneri.appickle.json.JsonCodec.SurveyFieldAdapter.FieldType;

public class ToggleField extends SurveyField
{
    private Boolean selected;

    public ToggleField(String id,
                       String description,
                       String error,
                       Boolean required,
                       String result,
                       Boolean selected)
    {
        super(FieldType.toggle, id, description, error, required, result);

        this.selected = selected;
    }

    public Boolean isSelected()
    {
        return (selected != null) && selected;
    }
}