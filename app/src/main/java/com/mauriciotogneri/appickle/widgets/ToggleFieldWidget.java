package com.mauriciotogneri.appickle.widgets;

import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ScrollView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.fields.ToggleField;
import com.mauriciotogneri.appickle.pickers.PickerSelector;

public class ToggleFieldWidget extends SurveyFieldWidget
{
    private final ToggleField toggleField;

    public ToggleFieldWidget(ToggleField toggleField, ScrollView containerScrollView)
    {
        super(toggleField, containerScrollView);

        this.toggleField = toggleField;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup parent, PickerSelector selector)
    {
        View view = inflate(inflater, parent, R.layout.view_field_toggle);

        SwitchCompat toggle = (SwitchCompat) view.findViewById(R.id.field_toggle);

        toggle.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked)
            {
                toggleField.result(String.valueOf(checked));
            }
        });

        toggle.setChecked(toggleField.isSelected());
    }
}