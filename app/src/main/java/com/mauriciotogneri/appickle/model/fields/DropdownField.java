package com.mauriciotogneri.appickle.model.fields;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.json.JsonSurveyField.Type;

import java.util.ArrayList;
import java.util.List;

public class DropdownField extends SelectableField<View>
{
    private Spinner spinner;

    public DropdownField(Type type,
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
    public void init(LayoutInflater inflater, ViewGroup parent)
    {
        View view = inflate(inflater, parent, R.layout.field_dropdown_group);

        List<String> list = new ArrayList<>();
        int selectedIndex = -1;

        for (int i = 0; i < values.size(); i++)
        {
            FieldValue value = values.get(i);

            list.add(value.label());

            if (value.isSelected())
            {
                selectedIndex = i;
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), R.layout.field_dropdown_item, list);
        adapter.setDropDownViewResource(R.layout.field_dropdown_element);

        Spinner spinner = (Spinner) view.findViewById(R.id.field_selectable_dropdownGroup);
        spinner.setAdapter(adapter);

        if (selectedIndex != -1)
        {
            spinner.setSelection(selectedIndex);
        }
    }

    @Override
    public String result()
    {
        if (spinner != null)
        {
            int position = spinner.getSelectedItemPosition();

            return values.get(position).key();
        }

        return null;
    }
}