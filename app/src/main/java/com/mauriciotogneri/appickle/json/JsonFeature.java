package com.mauriciotogneri.appickle.json;

import com.mauriciotogneri.appickle.model.Feature;
import com.mauriciotogneri.appickle.model.Scenario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonFeature
{
    private final String[] tags;
    private final String[] description;
    private final JsonBackground[] backgrounds;
    private final JsonScenario[] scenarios;

    public JsonFeature(String[] tags, String[] description, JsonBackground[] backgrounds, JsonScenario[] scenarios)
    {
        this.tags = tags;
        this.description = description;
        this.backgrounds = backgrounds;
        this.scenarios = scenarios;
    }

    public Feature model()
    {
        List<String> description = Arrays.asList(this.description);
        List<Scenario> backgrounds = new ArrayList<>();
        List<Scenario> scenarios = new ArrayList<>();

        for (JsonBackground background : this.backgrounds)
        {
            backgrounds.add(background.model());
        }

        for (JsonScenario scenario : this.scenarios)
        {
            scenarios.addAll(scenario.model());
        }

        return new Feature(description, backgrounds, scenarios);
    }
}