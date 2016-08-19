package com.mauriciotogneri.appickle.parser.tokens;

public class ExamplesToken extends Token
{
    private final String name;

    public ExamplesToken(String name)
    {
        this.name = name;
    }

    public String name()
    {
        return name;
    }

    @Override
    public boolean isExamples()
    {
        return true;
    }
}