package com.mauriciotogneri.appickle.activities;

import android.os.Bundle;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.uibinder.annotations.OnClick;

public class MainActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        toolbarTitle(R.string.app_name);
    }

    @OnClick(R.id.screen_main_button_new)
    public void onButtonNew()
    {
        startActivity(NewSessionActivity.createIntent(this));
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