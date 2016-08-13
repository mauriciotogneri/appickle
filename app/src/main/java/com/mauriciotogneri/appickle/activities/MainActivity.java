package com.mauriciotogneri.appickle.activities;

import android.os.Bundle;

import com.mauriciotogneri.appickle.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setTitle(R.string.app_name);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.screen_main_button_load)
    public void onButtonLoad()
    {
        openActivity(LoadActivity.class);
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

    private String getAsset(String path) throws IOException
    {
        //        try
        //        {
        //            String json = getAsset("feature.json");
        //            JsonFeature jsonFeature = new Gson().fromJson(json, JsonFeature.class);
        //            Feature feature = jsonFeature.model();
        //            System.out.println(feature);
        //        }
        //        catch (Exception e)
        //        {
        //            e.printStackTrace();
        //        }

        StringBuilder buf = new StringBuilder();
        InputStream json = getAssets().open(path);
        BufferedReader in =
                new BufferedReader(new InputStreamReader(json, "UTF-8"));
        String str;

        while ((str = in.readLine()) != null)
        {
            buf.append(str);
        }

        in.close();

        return buf.toString();
    }
}