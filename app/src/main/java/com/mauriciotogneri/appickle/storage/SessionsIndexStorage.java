package com.mauriciotogneri.appickle.storage;

import android.content.Context;

import com.mauriciotogneri.appickle.model.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SessionsIndexStorage extends BaseStorage
{
    private static final String ATTRIBUTE_SESSIONS_INDEX = "sessions.index";

    public SessionsIndexStorage(Context context)
    {
        super(context, "storage");
    }

    public List<Session> loadSessions()
    {
        List<Session> sessions = new ArrayList<>();

        for (String sessionId : loadSessionsIds())
        {
            SessionStorage sessionStorage = new SessionStorage(context(), sessionId);
            sessions.add(sessionStorage.loadSession());
        }

        return sessions;
    }

    public void saveSession(Session session)
    {
        String sessionId = session.id();

        SessionStorage sessionStorage = new SessionStorage(context(), sessionId);
        sessionStorage.saveSession(session);

        Set<String> sessionsIndex = loadSessionsIds();
        sessionsIndex.add(sessionId);

        saveSessions(sessionsIndex);
    }

    public Set<String> loadSessionsIds()
    {
        return getStringSet(ATTRIBUTE_SESSIONS_INDEX);
    }

    private void saveSessions(Set<String> sessions)
    {
        putStringSet(ATTRIBUTE_SESSIONS_INDEX, sessions);
    }
}