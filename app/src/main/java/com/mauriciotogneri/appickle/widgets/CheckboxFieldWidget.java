package com.mauriciotogneri.appickle.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ScrollView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.fields.CheckboxField;
import com.mauriciotogneri.appickle.model.fields.FieldValue;
import com.mauriciotogneri.appickle.pickers.PickerSelector;

public class CheckboxFieldWidget extends SurveyFieldWidget
{
    private final CheckboxField dropdownField;

    public CheckboxFieldWidget(CheckboxField dropdownField, ScrollView containerScrollView)
    {
        super(dropdownField, containerScrollView);

        this.dropdownField = dropdownField;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup parent, PickerSelector selector)
    {
        View view = inflate(inflater, parent, R.layout.view_field_checkbox_group);

        ViewGroup checkboxGroup = (ViewGroup) view.findViewById(R.id.field_selectable_checkboxGroup);

        for (FieldValue value : dropdownField.values())
        {
            CheckBox checkbox = (CheckBox) inflater.inflate(R.layout.view_field_checkbox_item, checkboxGroup, false);

            checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked)
                {
                    dropdownField.result(String.valueOf(checked));
                }
            });

            checkbox.setText(value.label());
            checkbox.setChecked(value.isSelected());
            checkbox.setTag(value.key());

            checkboxGroup.addView(checkbox);
        }
    }
}