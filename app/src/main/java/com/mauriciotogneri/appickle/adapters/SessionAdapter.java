package com.mauriciotogneri.appickle.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.adapters.SessionAdapter.ViewHolder;
import com.mauriciotogneri.appickle.base.BaseListAdapter;
import com.mauriciotogneri.appickle.base.BaseListAdapter.OnViewHolderClicked;
import com.mauriciotogneri.appickle.model.session.Session;

import java.util.List;

public class SessionAdapter extends BaseListAdapter<Session, ViewHolder> implements OnViewHolderClicked
{
    private final OnItemSelected<Session> onItemSelected;

    public SessionAdapter(Context context, List<Session> sessions, OnItemSelected<Session> onItemSelected)
    {
        super(context, R.layout.row_session, sessions);

        this.onItemSelected = onItemSelected;
    }

    @Override
    protected ViewHolder viewHolder(View view)
    {
        return new ViewHolder(view, this);
    }

    @Override
    protected void fillView(ViewHolder viewHolder, Session session)
    {
        viewHolder.title.setText(session.title());
        viewHolder.description.setText(session.description());
    }

    @Override
    public void onViewHolderClicked(int position)
    {
        onItemSelected.onItemSelected(item(position), position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView title;
        public final TextView description;
        private final OnViewHolderClicked onViewHolderClicked;

        public ViewHolder(View view, OnViewHolderClicked onViewHolderClicked)
        {
            super(view);

            this.title = (TextView) view.findViewById(R.id.session_title);
            this.description = (TextView) view.findViewById(R.id.session_description);

            this.onViewHolderClicked = onViewHolderClicked;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            onViewHolderClicked.onViewHolderClicked(getLayoutPosition());
        }
    }
}