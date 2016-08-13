package com.mauriciotogneri.appickle.storage;

import android.content.Context;

import com.mauriciotogneri.appickle.model.Session;

import java.util.Set;

public class SessionsIndexStorage extends BaseStorage
{
    private static final String ATTRIBUTE_SESSIONS_INDEX = "sessions.index";

    public SessionsIndexStorage(Context context)
    {
        super(context, "storage");
    }

    public Set<String> loadSessions()
    {
        return getStringSet(ATTRIBUTE_SESSIONS_INDEX);
    }

    public void saveSession(Session session)
    {
        String sessionId = session.id();

        SessionStorage sessionStorage = new SessionStorage(context(), sessionId);
        sessionStorage.saveSession(session);

        Set<String> sessionsIndex = loadSessions();
        sessionsIndex.add(sessionId);

        saveSessions(sessionsIndex);
    }

    private void saveSessions(Set<String> sessions)
    {
        putStringSet(ATTRIBUTE_SESSIONS_INDEX, sessions);
    }
}