package com.mauriciotogneri.appickle.parser.tokens;

public class AndToken extends Token
{
    private final String text;

    public AndToken(String text)
    {
        this.text = text;
    }

    public String text()
    {
        return text;
    }

    @Override
    public boolean isAnd()
    {
        return true;
    }
}