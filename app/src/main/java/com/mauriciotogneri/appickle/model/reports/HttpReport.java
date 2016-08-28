package com.mauriciotogneri.appickle.model.reports;

public class HttpReport extends Report
{
    private final String uri;

    public HttpReport(String uri, Output output)
    {
        super(output);

        this.uri = uri;
    }

    public String uri()
    {
        return uri;
    }
}