package com.mauriciotogneri.appickle.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.mauriciotogneri.appickle.R;

public abstract class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.screen_base);

        ViewGroup view = (ViewGroup) findViewById(R.id.content);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        layoutInflater.inflate(layout(), view, true);

        setupToolbar();
    }

    private void setupToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onBackButton();
            }
        });

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    protected abstract int layout();

    protected void onBackButton()
    {
    }
}