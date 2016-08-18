package com.mauriciotogneri.appickle.parser.tokens;

public class ExamplesToken extends Token
{
    public ExamplesToken()
    {
    }

    @Override
    public boolean isExamples()
    {
        return true;
    }
}