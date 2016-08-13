package com.mauriciotogneri.appickle.model;

import com.google.gson.Gson;

public class Session
{
    private final String id;

    public Session(String id)
    {
        this.id = id;
    }

    public String id()
    {
        return id;
    }

    public String json()
    {
        return new Gson().toJson(this);
    }

    public static Session fromJson(String json)
    {
        return new Gson().fromJson(json, Session.class);
    }
}