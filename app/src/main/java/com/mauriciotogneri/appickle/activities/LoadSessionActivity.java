package com.mauriciotogneri.appickle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.adapters.SessionAdapter;
import com.mauriciotogneri.appickle.adapters.SimpleDividerItemDecoration;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.base.BaseListAdapter.OnItemSelected;
import com.mauriciotogneri.appickle.model.Session;
import com.mauriciotogneri.appickle.storage.SessionsIndexStorage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadSessionActivity extends BaseActivity implements OnItemSelected<Session>
{
    @BindView(R.id.session_list)
    public RecyclerView sessionList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        toolbarTitle(R.string.screen_load_title);

        SessionsIndexStorage sessionsIndexStorage = new SessionsIndexStorage(this);
        List<Session> sessions = sessionsIndexStorage.loadSessions();

        SessionAdapter adapter = new SessionAdapter(this, sessions, this);

        sessionList.setHasFixedSize(true);
        sessionList.setAdapter(adapter);
        sessionList.setLayoutManager(new LinearLayoutManager(this));
        sessionList.addItemDecoration(new SimpleDividerItemDecoration(this));
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_load;
    }

    @Override
    public void onItemSelected(Session session)
    {
        Intent intent = IntroSessionActivity.createIntent(this, session.id());
        startActivity(intent);
    }
}