package com.mauriciotogneri.appickle.model.gherkin;

import java.util.Iterator;
import java.util.List;

public class StandardScenario implements Scenario, Iterable<String>
{
    private final List<String> description;
    private final List<String> steps;

    public StandardScenario(List<String> description, List<String> steps)
    {
        this.description = description;
        this.steps = steps;
    }

    @Override
    public Iterator<String> iterator()
    {
        return steps.iterator();
    }
}