package com.mauriciotogneri.appickle.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseListAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V>
{
    private final int resourceId;
    private final List<T> items;
    private final LayoutInflater inflater;

    public BaseListAdapter(Context context, int resourceId, List<T> items)
    {
        this.resourceId = resourceId;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return viewHolder(inflater.inflate(resourceId, parent, false));
    }

    @Override
    public void onBindViewHolder(V viewHolder, int position)
    {
        fillView(viewHolder, items.get(position));
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    protected abstract V viewHolder(View view);

    protected abstract void fillView(V viewHolder, T item);

    protected T item(int position)
    {
        return items.get(position);
    }

    public void update()
    {
        notifyDataSetChanged();
    }

    public void update(List<T> list)
    {
        items.clear();
        items.addAll(list);

        update();
    }

    public void add(T element)
    {
        items.add(element);

        update();
    }

    public interface OnViewHolderClicked
    {
        void onViewHolderClicked(int position);
    }

    public interface OnItemSelected<T>
    {
        void onItemSelected(T item, int position);
    }
}