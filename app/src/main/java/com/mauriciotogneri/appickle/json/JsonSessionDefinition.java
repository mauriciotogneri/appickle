package com.mauriciotogneri.appickle.json;

import com.google.gson.Gson;
import com.mauriciotogneri.appickle.model.gherkin.Feature;
import com.mauriciotogneri.appickle.model.session.Session;
import com.mauriciotogneri.appickle.parser.FeatureParser;
import com.mauriciotogneri.appickle.parser.TokenType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonSessionDefinition
{
    private final String id;
    private final String title;
    private final String description;
    private final List<String> thumbnails;
    private final JsonSurvey survey;
    private final JsonReport report;
    private final JsonLocalization localization;
    private final List<String> features;

    public JsonSessionDefinition(String id, String title, String description, List<String> thumbnails, JsonSurvey survey, JsonReport report, JsonLocalization localization, List<String> features)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumbnails = thumbnails;
        this.survey = survey;
        this.report = report;
        this.localization = localization;
        this.features = features;
    }

    public Session model(List<String> rawFeatures) throws IOException
    {
        List<Feature> features = new ArrayList<>();

        TokenType tokenType = new TokenType(
                localization.feature(),
                localization.background(),
                localization.scenario(),
                localization.scenarioOutline(),
                localization.examples(),
                localization.given(),
                localization.when(),
                localization.then(),
                localization.and(),
                localization.but());

        for (String rawFeature : rawFeatures)
        {
            InputStream stream = new ByteArrayInputStream(rawFeature.getBytes("UTF-8"));
            FeatureParser parser = new FeatureParser(stream, tokenType);

            features.add(parser.feature());
        }

        return new Session(id, title, description, thumbnails, survey.model(), report.model(), features);
    }

    public List<String> features()
    {
        return features;
    }

    public static JsonSessionDefinition fromJsonString(String string)
    {
        return new Gson().fromJson(string, JsonSessionDefinition.class);
    }
}