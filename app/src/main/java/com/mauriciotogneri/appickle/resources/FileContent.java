package com.mauriciotogneri.appickle.resources;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileContent
{
    private final File file;

    public FileContent(Uri uri)
    {
        this.file = new File(uri.getPath());
    }

    // TODO: improve
    public String content() throws IOException
    {
        InputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");

        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;

        while ((line = bufferedReader.readLine()) != null)
        {
            builder.append(line);
        }

        bufferedReader.close();

        return builder.toString();
    }
}