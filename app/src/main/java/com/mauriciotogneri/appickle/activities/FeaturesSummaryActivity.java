package com.mauriciotogneri.appickle.activities;

import android.os.Bundle;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.base.BaseActivity;

public class FeaturesSummaryActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        toolbarTitle(R.string.screen_features_title);
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_features_summary;
    }
}