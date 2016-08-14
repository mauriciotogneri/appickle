package com.mauriciotogneri.appickle.model;

import com.google.gson.Gson;

public class Session
{
    private final String id;
    private final String title;
    private final String description;
    private final String[] thumbnails;
    private final Survey survey;
    private final Report report;
    private final String[] features;

    public Session(String id, String title, String description, String[] thumbnails, Survey survey, Report report, String[] features)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnails = thumbnails;
        this.survey = survey;
        this.report = report;
        this.features = features;
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

    public Survey survey()
    {
        return survey;
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