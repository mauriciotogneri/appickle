package com.mauriciotogneri.appickle.parser.tokens;

public class GivenToken extends Token
{
    private final String text;

    public GivenToken(String text)
    {
        this.text = text;
    }

    public String text()
    {
        return text;
    }

    @Override
    public boolean isGiven()
    {
        return true;
    }
}