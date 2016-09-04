package com.mauriciotogneri.appickle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.model.fields.DateField;
import com.mauriciotogneri.appickle.model.fields.SurveyField;
import com.mauriciotogneri.appickle.model.fields.TimeField;
import com.mauriciotogneri.appickle.model.session.Session;
import com.mauriciotogneri.appickle.model.session.Survey;
import com.mauriciotogneri.appickle.pickers.DatePickerFragment;
import com.mauriciotogneri.appickle.pickers.DatePickerFragment.OnDateSelected;
import com.mauriciotogneri.appickle.pickers.PickerSelector;
import com.mauriciotogneri.appickle.pickers.TimePickerFragment;
import com.mauriciotogneri.appickle.pickers.TimePickerFragment.OnTimeSelected;
import com.mauriciotogneri.appickle.storage.SessionStorage;
import com.mauriciotogneri.appickle.widgets.DateFieldWidget;
import com.mauriciotogneri.appickle.widgets.SurveyFieldWidget;
import com.mauriciotogneri.appickle.widgets.TimeFieldWidget;
import com.mauriciotogneri.uibinder.annotations.BindView;
import com.mauriciotogneri.uibinder.annotations.OnClick;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class SurveyActivity extends BaseActivity implements PickerSelector, OnDateSelected, OnTimeSelected
{
    private static final String PARAMETER_SESSION_ID = "session.id";

    private Session session;

    private final List<SurveyFieldWidget> widgets = new ArrayList<>();

    @BindView(R.id.screen_survey_description)
    public TextView surveyDescription;

    @BindView(R.id.screen_survey_scrollViewContainer)
    public ScrollView scrollViewContainer;

    @BindView(R.id.screen_survey_fieldContainer)
    public ViewGroup fieldContainer;

    public static Intent createIntent(Context context, String sessionId)
    {
        Bundle parameters = new Bundle();
        parameters.putString(PARAMETER_SESSION_ID, sessionId);

        Intent intent = new Intent(context, SurveyActivity.class);
        intent.putExtras(parameters);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        this.session = session();
        displaySurvey(session.survey());

        toolbarTitle(R.string.screen_survey_title);
    }

    private Session session()
    {
        String sessionId = parameter(PARAMETER_SESSION_ID);

        SessionStorage sessionStorage = new SessionStorage(this);

        return sessionStorage.entity(sessionId);
    }

    private void displaySurvey(Survey survey)
    {
        surveyDescription.setText(survey.description());

        LayoutInflater inflater = LayoutInflater.from(this);

        for (SurveyField field : survey.fields())
        {
            SurveyFieldWidget widget = field.widget(scrollViewContainer);
            widget.init(inflater, fieldContainer, this);

            widgets.add(widget);
        }
    }

    @OnClick(R.id.screen_survey_button_start)
    @SuppressWarnings("unused")
    public void onButtonStart()
    {
        if (validateSurvey())
        {
            String sessionId = parameter(PARAMETER_SESSION_ID);
            startActivity(FeaturesSummaryActivity.createIntent(this, sessionId));

            finish();
        }
    }

    @Override
    public void onPickDate(DateField dateField, String id)
    {
        DialogFragment newFragment = DatePickerFragment.create(dateField.dateTime(), id);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSelected(DateTime dateTime, String id)
    {
        for (SurveyFieldWidget widget : widgets)
        {
            if (TextUtils.equals(id, widget.id()))
            {
                DateFieldWidget dateFieldWidget = (DateFieldWidget) widget;
                dateFieldWidget.setDate(dateTime, this);
            }
        }
    }

    @Override
    public void onPickTime(TimeField timeField, String id)
    {
        DialogFragment newFragment = TimePickerFragment.create(timeField.time(), id);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSelected(DateTime dateTime, String id)
    {
        for (SurveyFieldWidget widget : widgets)
        {
            if (TextUtils.equals(id, widget.id()))
            {
                TimeFieldWidget timeFieldWidget = (TimeFieldWidget) widget;
                timeFieldWidget.setTime(dateTime);
            }
        }
    }

    private boolean validateSurvey()
    {
        for (SurveyFieldWidget widget : widgets)
        {
            if (!widget.validate())
            {
                return false;
            }
        }

        return true;
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_survey;
    }
}