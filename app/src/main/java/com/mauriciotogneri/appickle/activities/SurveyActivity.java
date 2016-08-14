package com.mauriciotogneri.appickle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.model.Survey;
import com.mauriciotogneri.appickle.model.SurveyField;
import com.mauriciotogneri.appickle.storage.SessionStorage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveyActivity extends BaseActivity
{
    private static final String PARAMETER_SESSION_ID = "session.id";

    private String sessionId;

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

        ButterKnife.bind(this);

        this.sessionId = parameter(PARAMETER_SESSION_ID);

        SessionStorage sessionStorage = new SessionStorage(this, sessionId);
        displaySurvey(sessionStorage.loadSession().survey());

        toolbarTitle(R.string.screen_survey_title);
    }

    private void displaySurvey(Survey survey)
    {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (SurveyField field : survey.fields())
        {
            addField(field, inflater);
        }
    }

    private void addField(SurveyField field, LayoutInflater inflater)
    {
        field.view(inflater, fieldContainer);
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_survey;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            Toast.makeText(SurveyActivity.this, "CLICKED ON TOOLBAR ICON!", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}