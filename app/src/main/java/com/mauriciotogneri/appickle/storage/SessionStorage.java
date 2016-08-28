package com.mauriciotogneri.appickle.storage;

import android.content.Context;

import com.mauriciotogneri.appickle.model.session.Session;
import com.mauriciotogneri.entitystorage.Converter;
import com.mauriciotogneri.entitystorage.EntityStorage;

public class SessionStorage extends EntityStorage<Session>
{
    private static final Converter<Session> converter = new Converter<Session>()
    {
        @Override
        public String key(Session session)
        {
            return session.id();
        }

        @Override
        public String content(Session session)
        {
            return session.toJson();
        }

        @Override
        public Session create(String key, String content)
        {
            return Session.fromJsonString(content);
        }
    };

    public SessionStorage(Context context)
    {
        super(context, "session", ".index", converter);
    }
}