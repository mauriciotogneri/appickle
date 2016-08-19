package com.mauriciotogneri.appickle.json;

import com.mauriciotogneri.appickle.json.gherkin.JsonFeature;
import com.mauriciotogneri.appickle.model.session.Session;

import java.util.List;

public class JsonSession extends JsonEntity<Session>
{
    private final String id;
    private final String title;
    private final String description;
    private final List<String> thumbnails;
    private final JsonSurvey survey;
    private final JsonReport report;
    private final List<JsonFeature> features;

    public JsonSession(String id, String title, String description, List<String> thumbnails, JsonSurvey survey, JsonReport report, List<JsonFeature> features)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnails = thumbnails;
        this.survey = survey;
        this.report = report;
        this.features = features;
    }

    @Override
    public Session model()
    {
        return new Session(id, title, description, thumbnails, survey.model(), report.model(), fromList(features));
    }
}