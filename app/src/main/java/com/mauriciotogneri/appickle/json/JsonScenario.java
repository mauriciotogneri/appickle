package com.mauriciotogneri.appickle.json;

import com.mauriciotogneri.appickle.model.Scenario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JsonScenario
{
    private final Type type;
    private final String[] tags;
    private final String[] description;
    private final String[] steps;
    private final JsonExample examples;

    private enum Type
    {
        standard,
        outline
    }

    public JsonScenario(Type type, String[] tags, String[] description, String[] steps, JsonExample examples)
    {
        this.type = type;
        this.tags = tags;
        this.description = description;
        this.steps = steps;
        this.examples = examples;
    }

    public List<Scenario> model()
    {
        switch (type)
        {
            case standard:
                return Arrays.asList(standard());

            case outline:
                return examples();
        }

        throw new RuntimeException();
    }

    private Scenario standard()
    {
        return new Scenario(Arrays.asList(description), Arrays.asList(steps));
    }

    private List<Scenario> examples()
    {
        List<Scenario> scenarios = new ArrayList<>();

        for (Map<String, String> map : examples)
        {
            scenarios.add(example(steps, map));
        }

        return scenarios;
    }

    private Scenario example(String[] baseSteps, Map<String, String> map)
    {
        List<String> steps = new ArrayList<>();

        for (String baseStep : baseSteps)
        {
            String filledStep = baseStep;

            for (Entry<String, String> entry : map.entrySet())
            {
                filledStep = filledStep.replace(String.format("<%s>", entry.getKey()), entry.getValue());
            }

            steps.add(filledStep);
        }

        return new Scenario(Arrays.asList(description), steps);
    }
}