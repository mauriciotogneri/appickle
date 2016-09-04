package com.mauriciotogneri.appickle.stats;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Statistics
{
    private final FirebaseAnalytics firebaseAnalytics;

    public Statistics(Context context)
    {
        this.firebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public void newFromQrCode()
    {
        firebaseAnalytics.logEvent("main.new.qrCode", new Bundle());
    }

    public void newFromQrFile()
    {
        firebaseAnalytics.logEvent("main.new.file", new Bundle());
    }
}