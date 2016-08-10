package com.mauriciotogneri.appickle.json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonExample implements Iterable<Map<String, String>>
{
    private final String[] tags;
    private final String[] headers;
    private final String[][] values;

    public JsonExample(String[] tags, String[] headers, String[][] values)
    {
        this.tags = tags;
        this.headers = headers;
        this.values = values;
    }

    @Override
    public Iterator<Map<String, String>> iterator()
    {
        return new ExampleIterator(headers, values);
    }

    private static class ExampleIterator implements Iterator<Map<String, String>>
    {
        private final String[] headers;
        private final String[][] values;
        private int index = 0;

        private ExampleIterator(String[] headers, String[][] values)
        {
            this.headers = headers;
            this.values = values;
        }

        @Override
        public boolean hasNext()
        {
            return index < values.length;
        }

        @Override
        public Map<String, String> next()
        {
            Map<String, String> map = new HashMap<>();

            for (int i = 0; i < headers.length; i++)
            {
                map.put(headers[i], values[index][i]);
            }

            index++;

            return map;
        }
    }
}