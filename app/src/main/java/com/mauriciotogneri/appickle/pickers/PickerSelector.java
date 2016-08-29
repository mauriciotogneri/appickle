package com.mauriciotogneri.appickle.pickers;

import com.mauriciotogneri.appickle.model.fields.DateField;
import com.mauriciotogneri.appickle.model.fields.TimeField;

public interface PickerSelector
{
    void onPickDate(DateField dateField, String id);

    void onPickTime(TimeField timeField, String id);
}