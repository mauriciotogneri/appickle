package com.mauriciotogneri.appickle.model.gherkin;

import java.util.Iterator;
import java.util.List;

public class Feature implements Iterable<Scenario>
{
    private final List<String> tags;
    private final String name;
    private final List<String> description;
    private final List<Background> backgrounds;
    private final List<Scenario> scenarios;

    public Feature(List<String> tags, String name, List<String> description, List<Background> backgrounds, List<Scenario> scenarios)
    {
        this.tags = tags;
        this.name = name;
        this.description = description;
        this.backgrounds = backgrounds;
        this.scenarios = scenarios;
    }

    @Override
    public Iterator<Scenario> iterator()
    {
        return scenarios.iterator();
    }
}