package com.mauriciotogneri.appickle.model.fields;

public class FieldValue
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
}