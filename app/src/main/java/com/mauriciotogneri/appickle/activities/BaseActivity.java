package com.mauriciotogneri.appickle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

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

        if (displayBackButton())
        {
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
    }

    protected void toolbarTitle(int titleId)
    {
        toolbarTitle(getString(titleId));
    }

    protected void toolbarTitle(String title)
    {
        setTitle("");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView titleLabel = (TextView) toolbar.findViewById(R.id.toolbar_title);
        titleLabel.setText(title);
    }

    @SuppressWarnings("unchecked")
    protected <T> T parameter(String key)
    {
        return (T) getIntent().getExtras().get(key);
    }

    protected void errorDialog(int message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(message);
        builder.setPositiveButton(R.string.dialog_ok, null);
        builder.show();
    }

    protected void openActivity(Class clazz)
    {
        openActivity(clazz, new Bundle());
    }

    protected void openActivity(Class clazz, Bundle parameters)
    {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(parameters);

        startActivity(intent);
    }

    protected abstract int layout();

    protected boolean displayBackButton()
    {
        return true;
    }

    protected void onBackButton()
    {
        finish();
    }
}