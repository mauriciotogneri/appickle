package com.mauriciotogneri.appickle.parser.tokens;

public class FeatureToken extends Token
{
    private final String name;

    public FeatureToken(String name)
    {
        this.name = name;
    }

    public String name()
    {
        return name;
    }

    @Override
    public boolean isFeature()
    {
        return true;
    }
}