package com.mauriciotogneri.appickle.parser.tokens;

import java.util.List;

public class TagsToken extends Token
{
    private final List<String> tags;

    public TagsToken(List<String> tags)
    {
        this.tags = tags;
    }

    public List<String> tags()
    {
        return tags;
    }

    @Override
    public boolean isTags()
    {
        return true;
    }
}