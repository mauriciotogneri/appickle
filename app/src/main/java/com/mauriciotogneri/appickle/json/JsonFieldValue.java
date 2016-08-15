package com.mauriciotogneri.appickle.json;

import com.mauriciotogneri.appickle.model.fields.FieldValue;

public class JsonFieldValue extends JsonEntity<FieldValue>
{
    private final String key;
    private final String label;
    private final Boolean selected;

    public JsonFieldValue(String key, String label, Boolean selected)
    {
        this.key = key;
        this.label = label;
        this.selected = selected;
    }

    @Override
    public FieldValue model()
    {
        return new FieldValue(key, label, selected);
    }
}