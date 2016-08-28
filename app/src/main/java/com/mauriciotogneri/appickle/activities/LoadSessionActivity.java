package com.mauriciotogneri.appickle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.adapters.SessionAdapter;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.base.BaseListAdapter.OnItemSelected;
import com.mauriciotogneri.appickle.model.session.Session;
import com.mauriciotogneri.appickle.storage.SessionStorage;
import com.mauriciotogneri.uibinder.annotations.BindView;

import java.util.List;

public class LoadSessionActivity extends BaseActivity implements OnItemSelected<Session>
{
    @BindView(R.id.session_list)
    public RecyclerView sessionList;

    public static Intent createIntent(Context context)
    {
        return new Intent(context, LoadSessionActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        toolbarTitle(R.string.screen_load_title);

        SessionStorage sessionStorage = new SessionStorage(this);
        List<Session> sessions = sessionStorage.entities();

        SessionAdapter adapter = new SessionAdapter(this, sessions, this);

        sessionList.setHasFixedSize(true);
        sessionList.setAdapter(adapter);
        sessionList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_load_session;
    }

    @Override
    public void onItemSelected(Session session)
    {
        Intent intent = IntroSessionActivity.createIntent(this, session.id());
        startActivity(intent);
    }
}