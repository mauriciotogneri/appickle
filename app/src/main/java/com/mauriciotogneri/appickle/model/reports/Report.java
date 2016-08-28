package com.mauriciotogneri.appickle.model.reports;

public class Report
{
    private final Output output;

    public enum Output
    {
        json,
        html
    }

    public Report(Output output)
    {
        this.output = output;
    }

    public Output output()
    {
        return output;
    }
}