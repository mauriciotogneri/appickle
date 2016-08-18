package com.mauriciotogneri.appickle.parser.tokens;

public class ScenarioToken extends Token
{
    private final String name;

    public ScenarioToken(String name)
    {
        this.name = name;
    }

    public String name()
    {
        return name;
    }

    @Override
    public boolean isScenario()
    {
        return true;
    }
}