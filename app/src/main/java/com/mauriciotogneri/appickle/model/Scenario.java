package com.mauriciotogneri.appickle.model;

import java.util.Iterator;
import java.util.List;

public class Scenario implements Iterable<String>
{
    private final List<String> description;
    private final List<String> steps;

    public Scenario(List<String> description, List<String> steps)
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