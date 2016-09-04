package com.mauriciotogneri.appickle.model.fields;

public class ToggleField extends SurveyField
{
    private final Boolean selected;

    public ToggleField(String id,
                       String description,
                       String error,
                       Boolean required,
                       String result,
                       Boolean selected)
    {
        super(id, description, error, required, result);

        this.selected = selected;
    }

    public Boolean isSelected()
    {
        return (selected != null) && selected;
    }
}