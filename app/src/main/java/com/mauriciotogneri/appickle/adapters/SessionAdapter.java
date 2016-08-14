package com.mauriciotogneri.appickle.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.adapters.SessionAdapter.ViewHolder;
import com.mauriciotogneri.appickle.base.BaseListAdapter;
import com.mauriciotogneri.appickle.model.Session;

import java.util.List;

public class SessionAdapter extends BaseListAdapter<Session, ViewHolder>
{
    public SessionAdapter(Context context, int resourceId, List<Session> list)
    {
        super(context, resourceId, list);
    }

    @Override
    protected void fillView(ViewHolder viewHolder, Session session, int position)
    {
        viewHolder.title.setText(session.title());
        viewHolder.description.setText(session.description());
    }

    @Override
    protected ViewHolder getViewHolder(View view)
    {
        return new ViewHolder(view);
    }

    protected static class ViewHolder
    {
        public final TextView title;
        public final TextView description;

        public ViewHolder(View view)
        {
            this.title = (TextView) view.findViewById(R.id.session_title);
            this.description = (TextView) view.findViewById(R.id.session_description);
        }
    }
}