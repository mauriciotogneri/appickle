package com.mauriciotogneri.appickle.model;

import com.mauriciotogneri.appickle.json.JsonSession;
import com.mauriciotogneri.appickle.model.reports.Report;

import java.util.List;

public class Session extends ModelEntity<JsonSession>
{
    private final String id;
    private final String title;
    private final String description;
    private final List<String> thumbnails;
    private final Survey survey;
    private final Report report;
    private final List<String> features;

    public Session(String id, String title, String description, List<String> thumbnails, Survey survey, Report report, List<String> features)
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

    @Override
    public JsonSession json()
    {
        return new JsonSession(id, title, description, thumbnails, survey.json(), report.json(), features);
    }
}