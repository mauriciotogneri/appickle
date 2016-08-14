package com.mauriciotogneri.appickle.model.reports;

import com.mauriciotogneri.appickle.json.JsonReport;
import com.mauriciotogneri.appickle.json.JsonReport.Output;
import com.mauriciotogneri.appickle.json.JsonReport.Target;

public class EmailReport extends Report
{
    private final String email;
    private final String subject;

    public EmailReport(String email, String subject, Output output)
    {
        super(output);

        this.email = email;
        this.subject = subject;
    }

    @Override
    public JsonReport json()
    {
        return new JsonReport(Target.http, null, email, subject, output);
    }
}