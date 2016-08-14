package com.mauriciotogneri.appickle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.model.Session;
import com.mauriciotogneri.appickle.model.Survey;
import com.mauriciotogneri.appickle.model.fields.SurveyField;
import com.mauriciotogneri.appickle.storage.SessionStorage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SurveyActivity extends BaseActivity
{
    private static final String PARAMETER_SESSION_ID = "session.id";

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

        displaySurvey(session().survey());

        toolbarTitle(R.string.screen_survey_title);
    }

    private Session session()
    {
        String sessionId = parameter(PARAMETER_SESSION_ID);

        SessionStorage sessionStorage = new SessionStorage(this, sessionId);

        return sessionStorage.loadSession();
    }

    private void displaySurvey(Survey survey)
    {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (SurveyField field : survey.fields())
        {
            field.init(inflater, fieldContainer);
        }
    }


    @OnClick(R.id.screen_survey_button_start)
    public void onButtonStart()
    {
        if (validateSurvey())
        {
            openActivity(FeaturesSummaryActivity.class);
        }
    }

    private boolean validateSurvey()
    {
        Session session = session();

        for (SurveyField field : session.survey().fields())
        {
            if (!field.validate())
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