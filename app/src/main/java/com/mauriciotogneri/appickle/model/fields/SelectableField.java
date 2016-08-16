package com.mauriciotogneri.appickle.model.fields;

import android.view.View;

import com.mauriciotogneri.appickle.json.JsonSurveyField;
import com.mauriciotogneri.appickle.json.JsonSurveyField.Type;

import java.util.ArrayList;
import java.util.List;

public abstract class SelectableField<T extends View> extends SurveyField
{
    protected final List<T> elements = new ArrayList<>();
    protected final List<FieldValue> values;

    public SelectableField(Type type,
                           String id,
                           String description,
                           String error,
                           Boolean required,
                           String result,
                           List<FieldValue> values)
    {
        super(type, id, description, error, required, result);

        this.values = values;
    }

    @Override
    public final boolean isFilled()
    {
        return result() != null;
    }

    @Override
    public final JsonSurveyField json()
    {
        return new JsonSurveyField(id, type, description, error, required, null, null, null, fromList(values), null, result);
    }
}