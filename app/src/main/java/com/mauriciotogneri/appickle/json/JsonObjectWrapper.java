package com.mauriciotogneri.appickle.json;

import android.text.TextUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class JsonObjectWrapper
{
    private final JsonObject json;

    public JsonObjectWrapper(JsonElement json)
    {
        this.json = json.getAsJsonObject();
    }

    public String getString(String key)
    {
        return json.has(key) ? json.get(key).getAsString() : null;
    }

    public Boolean getBoolean(String key)
    {
        return json.has(key) ? json.get(key).getAsBoolean() : null;
    }

    public JsonArray getArray(String key)
    {
        return json.has(key) ? json.get(key).getAsJsonArray() : new JsonArray();
    }

    @SuppressWarnings("unchecked")
    public <T extends Enum> T getEnum(String key, Class<T> enumType)
    {
        String value = getString(key);

        for (Object object : enumType.getEnumConstants())
        {
            if (TextUtils.equals(value, object.toString()))
            {
                return (T) object;
            }
        }

        return null;
    }

    public JsonElement get(String key)
    {
        return json.has(key) ? json.get(key) : null;
    }

    public <T> List<T> getList(String key, Class<T> clazz, JsonDeserializationContext context)
    {
        List<T> result = new ArrayList<>();
        JsonArray array = getArray(key);

        for (int i = 0; i < array.size(); i++)
        {
            T surveyField = context.deserialize(array.get(i), clazz);
            result.add(surveyField);
        }

        return result;
    }
}