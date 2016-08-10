package com.mauriciotogneri.appickle.model;

import java.util.Iterator;
import java.util.List;

public class Feature implements Iterable<Scenario>
{
    private final List<String> description;
    private final List<Scenario> backgrounds;
    private final List<Scenario> scenarios;

    public Feature(List<String> description, List<Scenario> backgrounds, List<Scenario> scenarios)
    {
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