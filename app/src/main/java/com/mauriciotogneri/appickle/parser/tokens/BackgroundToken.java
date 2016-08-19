package com.mauriciotogneri.appickle.parser.tokens;

public class BackgroundToken extends Token
{
    private final String name;

    public BackgroundToken(String name)
    {
        this.name = name;
    }

    public String name()
    {
        return name;
    }

    @Override
    public boolean isBackground()
    {
        return true;
    }
}