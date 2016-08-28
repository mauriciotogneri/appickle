package com.mauriciotogneri.appickle.model.reports;

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

    public String email()
    {
        return email;
    }

    public String subject()
    {
        return subject;
    }
}