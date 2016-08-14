package com.mauriciotogneri.appickle.model;

import com.mauriciotogneri.appickle.json.JsonEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class ModelEntity<J extends JsonEntity>
{
    public abstract J json();

    public static <M extends ModelEntity<J>, J extends JsonEntity<M>> List<J> fromList(List<M> entities)
    {
        List<J> list = new ArrayList<>();

        for (ModelEntity<J> entity : entities)
        {
            list.add(entity.json());
        }

        return list;
    }
}