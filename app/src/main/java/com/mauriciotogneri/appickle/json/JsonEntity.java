package com.mauriciotogneri.appickle.json;

import com.mauriciotogneri.appickle.model.ModelEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class JsonEntity<M extends ModelEntity>
{
    public abstract M model();

    public static <M extends ModelEntity<J>, J extends JsonEntity<M>> List<M> fromList(List<J> entities)
    {
        List<M> list = new ArrayList<>();

        for (JsonEntity<M> entity : entities)
        {
            list.add(entity.model());
        }

        return list;
    }
}