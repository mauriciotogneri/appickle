package com.mauriciotogneri.appickle.activities;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.uibinder.annotations.OnClick;

public class MainActivity extends BaseActivity
{
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        toolbarTitle(R.string.app_name);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    @OnClick(R.id.screen_main_button_new)
    public void onButtonNew()
    {
        startActivity(NewSessionActivity.createIntent(this));

        Bundle bundle = new Bundle();
        bundle.putString("param", "value");
        firebaseAnalytics.logEvent("main.event.new", bundle);
    }

    @OnClick(R.id.screen_main_button_load)
    public void onButtonLoad()
    {
        startActivity(LoadSessionActivity.createIntent(this));
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_main;
    }

    @Override
    protected boolean displayBackButton()
    {
        return false;
    }
}