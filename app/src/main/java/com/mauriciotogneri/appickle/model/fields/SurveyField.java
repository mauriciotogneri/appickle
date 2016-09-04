package com.mauriciotogneri.appickle.model.fields;

import android.text.TextUtils;
import android.widget.ScrollView;

import com.mauriciotogneri.appickle.widgets.CheckboxFieldWidget;
import com.mauriciotogneri.appickle.widgets.DateFieldWidget;
import com.mauriciotogneri.appickle.widgets.DropdownFieldWidget;
import com.mauriciotogneri.appickle.widgets.RadioFieldWidget;
import com.mauriciotogneri.appickle.widgets.SurveyFieldWidget;
import com.mauriciotogneri.appickle.widgets.TextFieldWidget;
import com.mauriciotogneri.appickle.widgets.TimeFieldWidget;
import com.mauriciotogneri.appickle.widgets.ToggleFieldWidget;

public abstract class SurveyField
{
    private final String id;
    private final String description;
    private final String error;
    private final Boolean required;
    private String result;

    protected SurveyField(String id, String description, String error, Boolean required, String result)
    {
        this.id = id;
        this.description = description;
        this.error = error;
        this.required = required;
        this.result = result;
    }

    public String id()
    {
        return id;
    }

    public String description()
    {
        return description;
    }

    public String error()
    {
        return error;
    }

    public Boolean required()
    {
        return (required != null) && (required);
    }

    public Boolean isEmpty()
    {
        return TextUtils.isEmpty(result);
    }

    public String result()
    {
        return result;
    }

    public void result(String result)
    {
        this.result = result;
    }

    public SurveyFieldWidget widget(ScrollView containerScrollView)
    {
        if (this instanceof TextField)
        {
            return new TextFieldWidget((TextField) this, containerScrollView);
        }
        else if (this instanceof RadioField)
        {
            return new RadioFieldWidget((RadioField) this, containerScrollView);
        }
        else if (this instanceof DropdownField)
        {
            return new DropdownFieldWidget((DropdownField) this, containerScrollView);
        }
        else if (this instanceof CheckboxField)
        {
            return new CheckboxFieldWidget((CheckboxField) this, containerScrollView);
        }
        else if (this instanceof ToggleField)
        {
            return new ToggleFieldWidget((ToggleField) this, containerScrollView);
        }
        else if (this instanceof DateField)
        {
            return new DateFieldWidget((DateField) this, containerScrollView);
        }
        else if (this instanceof TimeField)
        {
            return new TimeFieldWidget((TimeField) this, containerScrollView);
        }
        else
        {
            throw new RuntimeException();
        }
    }
}