package com.mauriciotogneri.appickle.widgets;

import android.graphics.Point;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.fields.SurveyField;
import com.mauriciotogneri.appickle.pickers.PickerSelector;

public abstract class SurveyFieldWidget
{
    private final SurveyField field;
    private final ScrollView containerScrollView;
    private TextView errorLabel;

    public SurveyFieldWidget(SurveyField field, ScrollView containerScrollView)
    {
        this.field = field;
        this.containerScrollView = containerScrollView;
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

                Point childOffset = new Point();
                deepChildOffset(containerScrollView, errorLabel.getParent().getParent(), (ViewGroup)errorLabel.getParent(), childOffset);
                containerScrollView.smoothScrollTo(0, childOffset.y);
            }
        }

        return valid;
    }

    private void deepChildOffset(ViewGroup mainParent, ViewParent parent, View child, Point accumulatedOffset)
    {
        ViewGroup parentGroup = (ViewGroup) parent;
        accumulatedOffset.x += child.getLeft();
        accumulatedOffset.y += child.getTop();

        if (parentGroup.equals(mainParent))
        {
            return;
        }

        deepChildOffset(mainParent, parentGroup.getParent(), parentGroup, accumulatedOffset);
    }
}