package com.mauriciotogneri.appickle.json;

import com.mauriciotogneri.appickle.model.ModelEntity;

public class JsonBackground extends JsonEntity // TODO
{
    private final String[] tags;
    private final String[] steps;

    public JsonBackground(String[] tags, String[] steps)
    {
        this.tags = tags;
        this.steps = steps;
    }

    @Override
    public ModelEntity model()
    {
        return null;
        //return new Scenario(new ArrayList<String>(), Arrays.asList(steps));
    }
}