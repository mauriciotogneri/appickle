package com.mauriciotogneri.appickle.parser.tokens;

public class ButToken extends Token
{
    private final String text;

    public ButToken(String text)
    {
        this.text = text;
    }

    public String text()
    {
        return text;
    }

    @Override
    public boolean isBut()
    {
        return true;
    }
}