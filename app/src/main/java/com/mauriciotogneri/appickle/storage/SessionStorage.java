package com.mauriciotogneri.appickle.storage;

import android.content.Context;

import com.mauriciotogneri.appickle.model.Session;

public class SessionStorage extends BaseStorage
{
    private static final String ATTRIBUTE_JSON = "session.json";

    public SessionStorage(Context context, String sessionId)
    {
        super(context, String.format("session_%s", sessionId));
    }

    public Session loadSession()
    {
        String json = getString(ATTRIBUTE_JSON, "{}");

        return Session.fromJson(json);
    }

    public void saveSession(Session session)
    {
        putString(ATTRIBUTE_JSON, session.json());
    }
}