package com.mauriciotogneri.appickle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.adapters.FeatureAdapter;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.base.BaseListAdapter.OnItemSelected;
import com.mauriciotogneri.appickle.model.session.Feature;
import com.mauriciotogneri.appickle.model.session.Session;
import com.mauriciotogneri.appickle.storage.SessionStorage;
import com.mauriciotogneri.appickle.widgets.CustomRecyclerView;
import com.mauriciotogneri.uibinder.annotations.BindView;

import java.util.List;

public class FeaturesSummaryActivity extends BaseActivity implements OnItemSelected<Feature>
{
    private static final String PARAMETER_SESSION_ID = "session.id";

    @BindView(R.id.features_list)
    public CustomRecyclerView featuresList;

    @BindView(R.id.label_list_empty)
    public View emptyLabel;

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
        List<Feature> features = session.parsedFeature();

        if (!features.isEmpty())
        {
            featuresList.fill(this, new FeatureAdapter(this, features, this));
        }
        else
        {
            featuresList.setVisibility(View.GONE);
            emptyLabel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_features_summary;
    }

    @Override
    public void onItemSelected(Feature feature, int position)
    {
        String sessionId = parameter(PARAMETER_SESSION_ID);
        startActivity(ScenariosSummaryActivity.createIntent(this, sessionId, position));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_features, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.menu_features_intro)
        {
            String sessionId = parameter(PARAMETER_SESSION_ID);
            startActivity(IntroSessionActivity.createIntent(this, sessionId, false));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}