package com.mauriciotogneri.appickle.model;

public class Report
{
    private final String url;
    private final Auth auth;

    public Report(String url, Auth auth)
    {
        this.url = url;
        this.auth = auth;
    }
}