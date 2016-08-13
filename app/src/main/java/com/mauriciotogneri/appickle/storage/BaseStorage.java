package com.mauriciotogneri.appickle.storage;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class BaseStorage
{
    private final Context context;
    private final SharedPreferences preferences;

    public BaseStorage(Context context, String name)
    {
        this.context = context;
        this.preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    protected Context context()
    {
        return context;
    }

    protected String getString(String key, String defaultValue)
    {
        return preferences.getString(key, defaultValue);
    }

    protected void putString(String key, String value)
    {
        preferences.edit().putString(key, value).apply();
    }

    protected Set<String> getStringSet(String key)
    {
        return preferences.getStringSet(key, new HashSet<String>());
    }

    protected void putStringSet(String key, Set<String> value)
    {
        preferences.edit().putStringSet(key, value).apply();
    }
}