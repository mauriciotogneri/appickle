package com.mauriciotogneri.appickle.model.fields;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.json.JsonSurveyField.Type;
import com.mauriciotogneri.appickle.pickers.PickerSelector;

import java.util.List;

public class CheckboxField extends SelectableField<CheckBox>
{
    public CheckboxField(Type type,
                         String id,
                         String description,
                         String error,
                         Boolean required,
                         String result,
                         List<FieldValue> values)
    {
        super(type, id, description, error, required, result, values);
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup parent, PickerSelector selector)
    {
        View view = inflate(inflater, parent, R.layout.view_field_checkbox_group);

        ViewGroup checkboxGroup = (ViewGroup) view.findViewById(R.id.field_selectable_checkboxGroup);

        for (FieldValue value : values)
        {
            CheckBox checkbox = (CheckBox) inflater.inflate(R.layout.view_field_checkbox_item, checkboxGroup, false);
            checkbox.setText(value.label());
            checkbox.setChecked(value.isSelected());
            checkbox.setTag(value.key());

            checkboxGroup.addView(checkbox);
            elements.add(checkbox);
        }
    }

    @Override
    public String result()
    {
        for (CheckBox checkbox : elements)
        {
            if (checkbox.isChecked())
            {
                return checkbox.getTag().toString();
            }
        }

        return null;
    }
}