package com.mauriciotogneri.appickle.model;

import com.google.gson.Gson;

public class Session
{
    private final String id;
    private final String title;
    private final String description;
    private final String[] thumbnails;

    public Session(String id, String title, String description, String[] thumbnails)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnails = thumbnails;
    }

    public String id()
    {
        return id;
    }

    public String title()
    {
        return title;
    }

    public String description()
    {
        return description;
    }

    public String[] thumbnails()
    {
        return thumbnails;
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