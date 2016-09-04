package com.mauriciotogneri.appickle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.adapters.SessionAdapter;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.base.BaseListAdapter.OnItemSelected;
import com.mauriciotogneri.appickle.model.session.Session;
import com.mauriciotogneri.appickle.storage.SessionStorage;
import com.mauriciotogneri.appickle.widgets.CustomRecyclerView;
import com.mauriciotogneri.uibinder.annotations.BindView;

import java.util.List;

public class LoadSessionActivity extends BaseActivity implements OnItemSelected<Session>
{
    @BindView(R.id.session_list)
    public CustomRecyclerView sessionsList;

    @BindView(R.id.label_list_empty)
    public View emptyLabel;

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

        if (!sessions.isEmpty())
        {
            sessionsList.fill(this, new SessionAdapter(this, sessions, this));
        }
        else
        {
            sessionsList.setVisibility(View.GONE);
            emptyLabel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_load_session;
    }

    @Override
    public void onItemSelected(Session session, int position)
    {
        startActivity(FeaturesSummaryActivity.createIntent(this, session.id()));

        finish();
    }
}