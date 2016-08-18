package com.mauriciotogneri.appickle.parser.tokens;

public class FreeTextToken extends Token
{
    private final String text;

    public FreeTextToken(String text)
    {
        this.text = text;
    }

    public String text()
    {
        return text;
    }

    @Override
    public boolean isFreeText()
    {
        return true;
    }
}