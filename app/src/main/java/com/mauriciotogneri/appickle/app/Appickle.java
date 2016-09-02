package com.mauriciotogneri.appickle.app;

import android.app.Application;
import android.os.StrictMode;

import com.mauriciotogneri.appickle.BuildConfig;

public class Appickle extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        if (BuildConfig.DEBUG)
        {
            StrictMode.ThreadPolicy.Builder threadBuilder = new StrictMode.ThreadPolicy.Builder();
            threadBuilder.detectAll();
            threadBuilder.penaltyLog();
            StrictMode.setThreadPolicy(threadBuilder.build());

            StrictMode.VmPolicy.Builder vmBuilder = new StrictMode.VmPolicy.Builder();
            vmBuilder.detectAll();
            vmBuilder.penaltyLog();
            StrictMode.setVmPolicy(vmBuilder.build());
        }
    }
}