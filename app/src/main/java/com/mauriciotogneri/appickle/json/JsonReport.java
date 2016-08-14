package com.mauriciotogneri.appickle.json;

import com.mauriciotogneri.appickle.model.reports.EmailReport;
import com.mauriciotogneri.appickle.model.reports.HttpReport;
import com.mauriciotogneri.appickle.model.reports.Report;

public class JsonReport extends JsonEntity<Report>
{
    private final Target target;
    private final String url;
    private final String email;
    private final String subject;
    private final Output output;

    public JsonReport(Target target, String url, String email, String subject, Output output)
    {
        this.target = target;
        this.url = url;
        this.email = email;
        this.subject = subject;
        this.output = output;
    }

    public enum Target
    {
        http,
        email
    }

    public enum Output
    {
        json,
        html
    }

    @Override
    public Report model()
    {
        switch (target)
        {
            case http:
                return new HttpReport(url, output);

            case email:
                return new EmailReport(email, subject, output);
        }

        throw new RuntimeException();
    }
}