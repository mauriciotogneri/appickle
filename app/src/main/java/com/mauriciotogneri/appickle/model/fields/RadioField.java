package com.mauriciotogneri.appickle.model.fields;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.json.JsonSurveyField.Type;

import java.util.List;

public class RadioField extends SelectableField<RadioButton>
{
    public RadioField(Type type,
                      String id,
                      String description,
                      Boolean required,
                      String result,
                      List<FieldValue> values)
    {
        super(type, id, description, required, result, values);
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup parent)
    {
        View view = inflate(inflater, parent, R.layout.field_radio_group);

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.field_selectable_radioGroup);
        int index = 1;

        for (FieldValue value : values)
        {
            RadioButton button = (RadioButton) inflater.inflate(R.layout.field_radio, radioGroup, false);
            button.setId(index++);
            button.setText(value.label());
            button.setTag(value.key());

            radioGroup.addView(button);

            if (value.isSelected())
            {
                radioGroup.check(button.getId());
            }

            elements.add(button);
        }
    }

    @Override
    public String result()
    {
        for (RadioButton button : elements)
        {
            if (button.isChecked())
            {
                return button.getTag().toString();
            }
        }

        return null;
    }
}