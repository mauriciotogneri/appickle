package com.mauriciotogneri.appickle.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.fields.DropdownField;
import com.mauriciotogneri.appickle.model.fields.FieldValue;
import com.mauriciotogneri.appickle.pickers.PickerSelector;

import java.util.ArrayList;
import java.util.List;

public class DropdownFieldWidget extends SurveyFieldWidget
{
    private final DropdownField dropdownField;

    public DropdownFieldWidget(DropdownField dropdownField, ScrollView containerScrollView)
    {
        super(dropdownField, containerScrollView);

        this.dropdownField = dropdownField;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup parent, PickerSelector selector)
    {
        View view = inflate(inflater, parent, R.layout.view_field_dropdown_group);

        List<String> list = new ArrayList<>();
        int selectedIndex = -1;

        for (int i = 0; i < dropdownField.values().size(); i++)
        {
            FieldValue value = dropdownField.values().get(i);

            list.add(value.label());

            if (value.isSelected())
            {
                selectedIndex = i;
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), R.layout.view_field_dropdown_item, list);
        adapter.setDropDownViewResource(R.layout.view_field_dropdown_element);

        Spinner spinner = (Spinner) view.findViewById(R.id.field_selectable_dropdownGroup);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                dropdownField.result(adapterView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
            }
        });

        if (selectedIndex != -1)
        {
            spinner.setSelection(selectedIndex);
        }
    }
}