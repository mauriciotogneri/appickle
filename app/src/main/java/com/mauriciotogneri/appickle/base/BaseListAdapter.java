package com.mauriciotogneri.appickle.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public abstract class BaseListAdapter<T, V> extends ArrayAdapter<T>
{
    protected final LayoutInflater inflater;
    private final int resourceId;

    public BaseListAdapter(Context context, int resourceId, List<T> list)
    {
        super(context, resourceId, list);

        this.inflater = LayoutInflater.from(context);
        this.resourceId = resourceId;
    }

    protected abstract void fillView(V viewHolder, T item, int position);

    protected abstract V getViewHolder(View view);

    public void update()
    {
        notifyDataSetChanged();
    }

    public void update(List<T> list)
    {
        clear();
        addAll(list);
        notifyDataSetChanged();
    }

    @Override
    @SuppressWarnings("unchecked")
    public View getView(int position, View convertView, ViewGroup parent)
    {
        V viewHolder;
        View rowView = convertView;

        if (rowView == null)
        {
            rowView = inflater.inflate(resourceId, parent, false);

            viewHolder = getViewHolder(rowView);
            rowView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (V) rowView.getTag();
        }

        T item = getItem(position);

        fillView(viewHolder, item, position);

        return rowView;
    }
}