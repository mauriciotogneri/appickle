package com.mauriciotogneri.appickle.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.fields.FieldValue;
import com.mauriciotogneri.appickle.model.fields.RadioField;
import com.mauriciotogneri.appickle.pickers.PickerSelector;

public class RadioFieldWidget extends SurveyFieldWidget
{
    private final RadioField radioField;

    public RadioFieldWidget(RadioField radioField, ScrollView containerScrollView)
    {
        super(radioField, containerScrollView);

        this.radioField = radioField;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup parent, PickerSelector selector)
    {
        View view = inflate(inflater, parent, R.layout.view_field_radio_group);

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.field_selectable_radioGroup);

        for (FieldValue value : radioField.values())
        {
            RadioButton radioButton = (RadioButton) inflater.inflate(R.layout.view_field_radio_item, radioGroup, false);
            radioGroup.addView(radioButton);

            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked)
                {
                    radioField.result(String.valueOf(checked));
                }
            });

            radioButton.setText(value.label());
            radioButton.setTag(value.key());
            radioButton.setChecked(value.isSelected());
        }
    }
}