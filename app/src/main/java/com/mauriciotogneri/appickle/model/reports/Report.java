package com.mauriciotogneri.appickle.model.reports;

import com.mauriciotogneri.appickle.json.JsonReport;
import com.mauriciotogneri.appickle.json.JsonReport.Output;
import com.mauriciotogneri.appickle.model.ModelEntity;

public abstract class Report extends ModelEntity<JsonReport>
{
    protected final Output output;

    protected Report(Output output)
    {
        this.output = output;
    }
}