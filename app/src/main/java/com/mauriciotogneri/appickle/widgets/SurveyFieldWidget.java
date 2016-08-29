package com.mauriciotogneri.appickle.widgets;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.fields.CheckboxField;
import com.mauriciotogneri.appickle.model.fields.DateField;
import com.mauriciotogneri.appickle.model.fields.DropdownField;
import com.mauriciotogneri.appickle.model.fields.RadioField;
import com.mauriciotogneri.appickle.model.fields.SurveyField;
import com.mauriciotogneri.appickle.model.fields.TextField;
import com.mauriciotogneri.appickle.model.fields.TimeField;
import com.mauriciotogneri.appickle.model.fields.ToggleField;
import com.mauriciotogneri.appickle.pickers.PickerSelector;

public abstract class SurveyFieldWidget
{
    private final SurveyField field;
    private TextView errorLabel;

    public SurveyFieldWidget(SurveyField field)
    {
        this.field = field;
    }

    public String id()
    {
        return field.id();
    }

    public View inflate(LayoutInflater inflater, ViewGroup parent, int resourceId)
    {
        View view = inflater.inflate(R.layout.view_field_base, parent, false);
        parent.addView(view);

        ViewGroup container = (ViewGroup) view.findViewById(R.id.field_base_container);
        container.addView(inflater.inflate(resourceId, container, false));

        TextView descriptionLabel = (TextView) view.findViewById(R.id.field_description);

        if (!TextUtils.isEmpty(field.description()))
        {
            descriptionLabel.setText(field.description());
        }
        else
        {
            descriptionLabel.setVisibility(View.GONE);
        }

        errorLabel = (TextView) view.findViewById(R.id.field_error);

        if (!TextUtils.isEmpty(field.error()))
        {
            errorLabel.setText(field.error());
        }

        return view;
    }

    public abstract void init(LayoutInflater inflater, ViewGroup parent, PickerSelector selector);

    public boolean validate()
    {
        boolean valid = (field.result() != null) || !field.required();

        if (valid)
        {
            errorLabel.setVisibility(View.GONE);
        }
        else
        {
            if (!TextUtils.isEmpty(field.error()))
            {
                errorLabel.setVisibility(View.VISIBLE);
            }
        }

        return valid;
    }

    public static SurveyFieldWidget fromField(SurveyField field)
    {
        if (field instanceof TextField)
        {
            return new TextFieldWidget((TextField) field);
        }
        else if (field instanceof RadioField)
        {
            return new RadioFieldWidget((RadioField) field);
        }
        else if (field instanceof DropdownField)
        {
            return new DropdownFieldWidget((DropdownField) field);
        }
        else if (field instanceof CheckboxField)
        {
            return new CheckboxFieldWidget((CheckboxField) field);
        }
        else if (field instanceof ToggleField)
        {
            return new ToggleFieldWidget((ToggleField) field);
        }
        else if (field instanceof DateField)
        {
            return new DateFieldWidget((DateField) field);
        }
        else if (field instanceof TimeField)
        {
            return new TimeFieldWidget((TimeField) field);
        }
        else
        {
            throw new RuntimeException();
        }
    }
}