package com.mauriciotogneri.appickle.network;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetRequest
{
    private final String url;

    public GetRequest(String url)
    {
        this.url = url;
    }

    public String content() throws IOException
    {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}