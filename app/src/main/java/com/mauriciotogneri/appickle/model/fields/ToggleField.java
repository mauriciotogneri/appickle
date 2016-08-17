package com.mauriciotogneri.appickle.model.fields;

import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.json.JsonSurveyField;
import com.mauriciotogneri.appickle.json.JsonSurveyField.Type;
import com.mauriciotogneri.appickle.pickers.PickerSelector;

public class ToggleField extends SurveyField
{
    private SwitchCompat toggle;
    private Boolean selected;

    public ToggleField(String id,
                       String description,
                       String error,
                       Boolean required,
                       String result,
                       Boolean selected)
    {
        super(Type.toggle, id, description, error, required, result);

        this.selected = selected;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup parent, PickerSelector selector)
    {
        View view = inflate(inflater, parent, R.layout.view_field_toggle);

        toggle = (SwitchCompat) view.findViewById(R.id.field_toggle);
        toggle.setChecked((selected != null) && selected);
    }

    @Override
    protected boolean isFilled()
    {
        return true;
    }

    @Override
    public String result()
    {
        return String.valueOf(toggle.isChecked());
    }

    @Override
    public JsonSurveyField json()
    {
        return new JsonSurveyField(id, type, description, error, required, null, null, null, null, selected, result);
    }
}