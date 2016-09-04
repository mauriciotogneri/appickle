package com.mauriciotogneri.appickle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.adapters.ScenarioAdapter;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.base.BaseListAdapter.OnItemSelected;
import com.mauriciotogneri.appickle.model.session.Feature;
import com.mauriciotogneri.appickle.model.session.Scenario;
import com.mauriciotogneri.appickle.model.session.Session;
import com.mauriciotogneri.appickle.storage.SessionStorage;
import com.mauriciotogneri.appickle.widgets.CustomRecyclerView;
import com.mauriciotogneri.uibinder.annotations.BindView;

import java.util.List;

public class ScenariosSummaryActivity extends BaseActivity implements OnItemSelected<Scenario>
{
    private static final String PARAMETER_SESSION_ID = "session.id";
    private static final String PARAMETER_FEATURE_POSITION = "feature.position";

    @BindView(R.id.scenarios_list)
    public CustomRecyclerView scenariosList;

    @BindView(R.id.label_list_empty)
    public View emptyLabel;

    public static Intent createIntent(Context context, String sessionId, int featurePosition)
    {
        Bundle parameters = new Bundle();
        parameters.putString(PARAMETER_SESSION_ID, sessionId);
        parameters.putInt(PARAMETER_FEATURE_POSITION, featurePosition);

        Intent intent = new Intent(context, ScenariosSummaryActivity.class);
        intent.putExtras(parameters);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        toolbarTitle(R.string.screen_scenarios_title);

        String sessionId = parameter(PARAMETER_SESSION_ID);
        Integer featurePosition = parameter(PARAMETER_FEATURE_POSITION);

        SessionStorage sessionStorage = new SessionStorage(this);
        Session session = sessionStorage.entity(sessionId);
        List<Feature> features = session.parsedFeature();
        List<Scenario> scenarios = features.get(featurePosition).scenarios();

        if (!features.isEmpty())
        {
            scenariosList.fill(this, new ScenarioAdapter(this, scenarios, this));
        }
        else
        {
            scenariosList.setVisibility(View.GONE);
            emptyLabel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_scenarios_summary;
    }

    @Override
    public void onItemSelected(Scenario scenario, int position)
    {
        String sessionId = parameter(PARAMETER_SESSION_ID);
        Integer featurePosition = parameter(PARAMETER_FEATURE_POSITION);

        startActivity(ScenarioActivity.createIntent(this, sessionId, featurePosition, position));
    }
}