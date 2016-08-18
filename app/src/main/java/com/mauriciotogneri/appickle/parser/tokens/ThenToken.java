package com.mauriciotogneri.appickle.parser.tokens;

public class ThenToken extends Token
{
    private final String text;

    public ThenToken(String text)
    {
        this.text = text;
    }

    public String text()
    {
        return text;
    }

    @Override
    public boolean isThen()
    {
        return true;
    }
}