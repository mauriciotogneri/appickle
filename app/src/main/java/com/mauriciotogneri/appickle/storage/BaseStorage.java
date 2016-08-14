package com.mauriciotogneri.appickle.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mauriciotogneri.appickle.json.JsonEntity;

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

    protected <T> T getJsonObject(String key, Class<?> clazz)
    {
        return getJsonEntity(key, "{}", clazz);
    }

    protected <T> T getJsonArray(String key, Class<?> clazz)
    {
        return getJsonEntity(key, "[]", clazz);
    }

    @SuppressWarnings("unchecked")
    private  <T> T getJsonEntity(String key, String defaultValue, Class<?> clazz)
    {
        String value = preferences.getString(key, defaultValue);

        return (T) new Gson().fromJson(value, clazz);
    }

    protected void putJson(String key, JsonEntity json)
    {
        String value = new Gson().toJson(json);

        preferences.edit().putString(key, value).apply();
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