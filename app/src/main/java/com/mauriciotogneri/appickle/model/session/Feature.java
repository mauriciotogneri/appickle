package com.mauriciotogneri.appickle.model.session;

import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ast.GherkinDocument;

public class Feature
{
    private final String content;
    private final gherkin.ast.Feature feature;
    private final Status status;

    public enum Status
    {
        PENDING,
        VALID,
        WARNING,
        ERROR
    }

    public Feature(String content, Status status)
    {
        this.content = content;
        this.status = status;
        this.feature = parseFeature(content);
    }

    private gherkin.ast.Feature parseFeature(String content)
    {
        Parser<GherkinDocument> parser = new Parser<>(new AstBuilder());
        GherkinDocument gherkinDocument = parser.parse(content);

        return gherkinDocument.getFeature();
    }

    public String name()
    {
        return feature.getName();
    }

    public String description()
    {
        String[] parts = feature.getDescription().split("\n");

        StringBuilder builder = new StringBuilder();

        for (String part : parts)
        {
            if (builder.length() != 0)
            {
                builder.append("\n");
            }

            builder.append(part.trim());
        }

        return builder.toString();
    }

    public Status status()
    {
        return status;
    }

    public String content()
    {
        return content;
    }
}