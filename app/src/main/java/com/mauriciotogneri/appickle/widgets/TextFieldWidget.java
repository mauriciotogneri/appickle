package com.mauriciotogneri.appickle.widgets;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.model.fields.TextField;
import com.mauriciotogneri.appickle.pickers.PickerSelector;

public class TextFieldWidget extends SurveyFieldWidget
{
    private final TextField textField;

    public TextFieldWidget(TextField textField, ScrollView containerScrollView)
    {
        super(textField, containerScrollView);

        this.textField = textField;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup parent, PickerSelector selector)
    {
        View view = inflate(inflater, parent, R.layout.view_field_standard);

        EditText editText = (EditText) view.findViewById(R.id.field_standard_input);

        editText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String text = charSequence.toString().trim();

                textField.result(TextUtils.isEmpty(text) ? null : text);
            }
        });

        if (textField.format() != null)
        {
            editText.setInputType(textField.format().value());
        }

        TextInputLayout inputLayout = (TextInputLayout) view.findViewById(R.id.field_standard_layout);

        if (!TextUtils.isEmpty(textField.placeholder()))
        {
            inputLayout.setHint(textField.placeholder());
        }

        if (!TextUtils.isEmpty(textField.defaultValue()))
        {
            editText.setText(textField.defaultValue());
        }
    }
}