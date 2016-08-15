package com.mauriciotogneri.appickle.model.fields;

import com.mauriciotogneri.appickle.json.JsonFieldValue;
import com.mauriciotogneri.appickle.model.ModelEntity;

public class FieldValue extends ModelEntity<JsonFieldValue>
{
    private final String key;
    private final String label;
    private final Boolean selected;

    public FieldValue(String key, String label, Boolean selected)
    {
        this.key = key;
        this.label = label;
        this.selected = selected;
    }

    public String key()
    {
        return key;
    }

    public String label()
    {
        return label;
    }

    public Boolean isSelected()
    {
        return (selected != null) && selected;
    }

    @Override
    public JsonFieldValue json()
    {
        return new JsonFieldValue(key, label, selected);
    }
}