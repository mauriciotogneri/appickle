package com.mauriciotogneri.appickle.json;

import com.mauriciotogneri.appickle.model.Scenario;

import java.util.ArrayList;
import java.util.Arrays;

public class JsonBackground
{
    private final String[] tags;
    private final String[] steps;

    public JsonBackground(String[] tags, String[] steps)
    {
        this.tags = tags;
        this.steps = steps;
    }

    public Scenario model()
    {
        return new Scenario(new ArrayList<String>(), Arrays.asList(steps));
    }
}