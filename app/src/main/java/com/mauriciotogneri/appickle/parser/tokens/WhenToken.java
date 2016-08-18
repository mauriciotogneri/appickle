package com.mauriciotogneri.appickle.parser.tokens;

public class WhenToken extends Token
{
    private final String text;

    public WhenToken(String text)
    {
        this.text = text;
    }

    public String text()
    {
        return text;
    }

    @Override
    public boolean isWhen()
    {
        return true;
    }
}