package com.mauriciotogneri.appickle.model.fields;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.json.JsonSurveyField;
import com.mauriciotogneri.appickle.json.JsonSurveyField.Type;

import java.util.ArrayList;
import java.util.List;

public class RadioField extends SurveyField
{
    private final List<RadioButton> buttons = new ArrayList<>();
    private final List<FieldValue> values;

    public RadioField(Type type,
                      String id,
                      String description,
                      Boolean required,
                      String result,
                      List<FieldValue> values)
    {
        super(type, id, description, required, result);

        this.values = values;
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

            buttons.add(button);
        }
    }

    @Override
    public boolean isFilled()
    {
        return result() != null;
    }

    @Override
    public String result()
    {
        for (RadioButton button : buttons)
        {
            if (button.isChecked())
            {
                return button.getTag().toString();
            }
        }

        return null;
    }

    @Override
    public JsonSurveyField json()
    {
        return new JsonSurveyField(id, type, description, required, null, null, null, fromList(values), result);
    }
}