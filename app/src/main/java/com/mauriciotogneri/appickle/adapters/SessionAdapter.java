package com.mauriciotogneri.appickle.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.adapters.SessionAdapter.ViewHolder;
import com.mauriciotogneri.appickle.model.Session;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<ViewHolder>
{
    private List<Session> sessions;
    private LayoutInflater inflater;

    public SessionAdapter(Context context, List<Session> sessions)
    {
        this.sessions = sessions;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewView = inflater.inflate(R.layout.row_session, parent, false);

        return new ViewHolder(viewView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        Session session = sessions.get(position);

        viewHolder.title.setText(session.title());
        viewHolder.description.setText(session.description());
    }

    @Override
    public int getItemCount()
    {
        return sessions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView title;
        public final TextView description;

        public ViewHolder(View view)
        {
            super(view);

            this.title = (TextView) view.findViewById(R.id.session_title);
            this.description = (TextView) view.findViewById(R.id.session_description);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            int position = getLayoutPosition();
            Session session = sessions.get(position);
            System.out.println(session);
        }
    }
}