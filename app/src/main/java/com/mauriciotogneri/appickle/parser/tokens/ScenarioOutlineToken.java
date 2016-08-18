package com.mauriciotogneri.appickle.parser.tokens;

public class ScenarioOutlineToken extends Token
{
    private final String name;

    public ScenarioOutlineToken(String name)
    {
        this.name = name;
    }

    public String name()
    {
        return name;
    }

    @Override
    public boolean isScenarioOutline()
    {
        return true;
    }
}