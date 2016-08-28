package com.mauriciotogneri.appickle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.adapters.StepAdapter;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.model.session.Feature;
import com.mauriciotogneri.appickle.model.session.Scenario;
import com.mauriciotogneri.appickle.model.session.Session;
import com.mauriciotogneri.appickle.model.session.Step;
import com.mauriciotogneri.appickle.storage.SessionStorage;
import com.mauriciotogneri.uibinder.annotations.BindView;
import com.mauriciotogneri.uibinder.annotations.OnClick;

import java.util.ArrayList;
import java.util.List;

public class ScenarioActivity extends BaseActivity
{
    private static final String PARAMETER_SESSION_ID = "session.id";
    private static final String PARAMETER_FEATURE_POSITION = "feature.position";
    private static final String PARAMETER_SCENARIO_POSITION = "scenario.position";

    @BindView(R.id.steps_list)
    public RecyclerView stepsList;

    private StepAdapter adapter;
    private int stepPosition = 0;
    private final List<Step> steps = new ArrayList<>();

    public static Intent createIntent(Context context, String sessionId, int featurePosition, int scenarioPosition)
    {
        Bundle parameters = new Bundle();
        parameters.putString(PARAMETER_SESSION_ID, sessionId);
        parameters.putInt(PARAMETER_FEATURE_POSITION, featurePosition);
        parameters.putInt(PARAMETER_SCENARIO_POSITION, scenarioPosition);

        Intent intent = new Intent(context, ScenarioActivity.class);
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
        Integer scenarioPosition = parameter(PARAMETER_SCENARIO_POSITION);

        SessionStorage sessionStorage = new SessionStorage(this);
        Session session = sessionStorage.entity(sessionId);
        List<Feature> features = session.parsedFeature();
        List<Scenario> scenarios = features.get(featurePosition).scenarios();
        steps.addAll(scenarios.get(scenarioPosition).steps());

        adapter = new StepAdapter(this, new ArrayList<Step>());

        stepsList.setHasFixedSize(true);
        stepsList.setAdapter(adapter);
        stepsList.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.scenario_screen)
    public void onStepListClick()
    {
        if (stepPosition < steps.size())
        {
            adapter.update(steps.subList(0, ++stepPosition));
        }
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_scenario;
    }
}