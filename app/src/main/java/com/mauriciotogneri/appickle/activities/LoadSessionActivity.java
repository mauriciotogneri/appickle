package com.mauriciotogneri.appickle.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.adapters.SessionAdapter;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.model.Session;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadSessionActivity extends BaseActivity
{
    private SessionAdapter adapter;

    @BindView(R.id.session_list)
    public ListView sessionList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        toolbarTitle(R.string.screen_load_title);

        List<Session> sessions = new ArrayList<>();
        sessions.add(new Session("1", "Title 1", "Description 1", new String[0]));
        sessions.add(new Session("2", "Title 2", "Description 2", new String[0]));
        sessions.add(new Session("3", "Title 3", "Description 3", new String[0]));

        this.adapter = new SessionAdapter(this, R.layout.row_session, sessions);

        sessionList.setAdapter(adapter);
        sessionList.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Session session = (Session) parent.getItemAtPosition(position);
                System.out.println(session);
            }
        });
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_load;
    }
}