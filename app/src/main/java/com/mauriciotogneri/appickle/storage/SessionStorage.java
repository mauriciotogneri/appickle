package com.mauriciotogneri.appickle.storage;

import android.content.Context;

import com.mauriciotogneri.appickle.json.JsonSession;
import com.mauriciotogneri.appickle.model.session.Session;

public class SessionStorage extends BaseStorage
{
    private static final String ATTRIBUTE_JSON = "session.json";

    public SessionStorage(Context context, String sessionId)
    {
        super(context, String.format("session_%s", sessionId));
    }

    public Session loadSession()
    {
        JsonSession json = getJsonObject(ATTRIBUTE_JSON, JsonSession.class);

        return json.model();
    }

    public void saveSession(Session session)
    {
        putJson(ATTRIBUTE_JSON, session.json());
    }
}