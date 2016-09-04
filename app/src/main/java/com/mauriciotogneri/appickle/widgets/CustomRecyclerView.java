package com.mauriciotogneri.appickle.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class CustomRecyclerView extends RecyclerView
{
    public CustomRecyclerView(Context context)
    {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public void fill(Context context, Adapter adapter)
    {
        setHasFixedSize(true);
        setAdapter(adapter);
        setLayoutManager(new LinearLayoutManager(context));
    }
}