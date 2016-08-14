package com.mauriciotogneri.appickle.model.reports;

import com.mauriciotogneri.appickle.json.JsonReport;
import com.mauriciotogneri.appickle.json.JsonReport.Output;
import com.mauriciotogneri.appickle.json.JsonReport.Target;

public class HttpReport extends Report
{
    private final String url;

    public HttpReport(String url, Output output)
    {
        super(output);

        this.url = url;
    }

    @Override
    public JsonReport json()
    {
        return new JsonReport(Target.http, url, null, null, output);
    }
}