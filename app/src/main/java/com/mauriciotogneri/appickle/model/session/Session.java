package com.mauriciotogneri.appickle.model.session;

import com.mauriciotogneri.appickle.json.JsonCodec;
import com.mauriciotogneri.appickle.model.reports.Report;

import java.util.List;

public class Session
{
    private final String id;
    private final String title;
    private final String description;
    private final List<String> thumbnails;
    private final Survey survey;
    private final Report report;
    private final List<Feature> features;

    public Session(String id, String title, String description, List<String> thumbnails, Survey survey, Report report, List<Feature> features)
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

    public List<String> thumbnails()
    {
        return thumbnails;
    }

    public Survey survey()
    {
        return survey;
    }

    public Report report()
    {
        return report;
    }

    public List<Feature> features()
    {
        return features;
    }

    public String toJson()
    {
        return new JsonCodec().toJson(this);
    }

    public static Session fromJsonString(String string)
    {
        return new JsonCodec().fromString(string, Session.class);
    }
}