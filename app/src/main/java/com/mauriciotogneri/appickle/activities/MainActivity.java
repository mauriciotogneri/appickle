package com.mauriciotogneri.appickle.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.json.JsonFeature;
import com.mauriciotogneri.appickle.model.Feature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setTitle("THE TITLE!");

        try
        {
            String json = getAsset("feature.json");
            JsonFeature jsonFeature = new Gson().fromJson(json, JsonFeature.class);
            Feature feature = jsonFeature.model();
            System.out.println(feature);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_main;
    }

    private String getAsset(String path) throws IOException
    {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}