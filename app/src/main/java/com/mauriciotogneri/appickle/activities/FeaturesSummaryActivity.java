package com.mauriciotogneri.appickle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.adapters.FeatureAdapter;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.base.BaseListAdapter.OnItemSelected;
import com.mauriciotogneri.appickle.model.session.Feature;
import com.mauriciotogneri.appickle.model.session.Session;
import com.mauriciotogneri.appickle.storage.SessionStorage;
import com.mauriciotogneri.uibinder.annotations.BindView;

import java.util.List;

public class FeaturesSummaryActivity extends BaseActivity implements OnItemSelected<Feature>
{
    private static final String PARAMETER_SESSION_ID = "session.id";

    @BindView(R.id.features_list)
    public RecyclerView sessionList;

    public static Intent createIntent(Context context, String sessionId)
    {
        Bundle parameters = new Bundle();
        parameters.putString(PARAMETER_SESSION_ID, sessionId);

        Intent intent = new Intent(context, FeaturesSummaryActivity.class);
        intent.putExtras(parameters);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        toolbarTitle(R.string.screen_features_title);

        String sessionId = parameter(PARAMETER_SESSION_ID);
        SessionStorage sessionStorage = new SessionStorage(this);
        Session session = sessionStorage.entity(sessionId);
        List<Feature> features = session.features();

        FeatureAdapter adapter = new FeatureAdapter(this, features, this);

        sessionList.setHasFixedSize(true);
        sessionList.setAdapter(adapter);
        sessionList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_features_summary;
    }

    @Override
    public void onItemSelected(Feature feature)
    {
        Intent intent = IntroSessionActivity.createIntent(this, feature.description());
        startActivity(intent);
    }
}