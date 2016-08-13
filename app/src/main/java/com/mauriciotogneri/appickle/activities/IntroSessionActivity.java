package com.mauriciotogneri.appickle.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroSessionActivity extends BaseActivity
{
    public static final String PARAMETER_SESSION_ID = "session.id";

    @BindView(R.id.sessionId)
    public TextView sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        //TODO setTitle(R.string.screen_load_title);

        String parameter = parameter(PARAMETER_SESSION_ID);
        sessionId.setText(parameter);
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_intro;
    }
}