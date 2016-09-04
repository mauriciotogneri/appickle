package com.mauriciotogneri.appickle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.adapters.StepAdapter;
import com.mauriciotogneri.appickle.base.BaseActivity;
import com.mauriciotogneri.appickle.model.session.Feature;
import com.mauriciotogneri.appickle.model.session.Scenario;
import com.mauriciotogneri.appickle.model.session.Session;
import com.mauriciotogneri.appickle.model.session.Step;
import com.mauriciotogneri.appickle.storage.SessionStorage;
import com.mauriciotogneri.appickle.widgets.CustomRecyclerView;
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
    public CustomRecyclerView stepsList;

    @BindView(R.id.scenario_button_next_enabled)
    public View buttonNextEnabled;

    @BindView(R.id.scenario_button_next_disabled)
    public View buttonNextDisabled;

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

        stepsList.fill(this, adapter);

        buttonNextDisabled.setEnabled(false);

        stepsList.setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    displayNextStep();
                }

                return false;
            }
        });
    }

    private void displayNextStep()
    {
        if (stepPosition < steps.size())
        {
            int position = stepPosition++;

            adapter.add(steps.get(position));

            stepsList.smoothScrollToPosition(position);
        }

        if (stepPosition >= steps.size())
        {
            enableButtonNext(true);
        }
    }

    @OnClick(R.id.scenario_button_comment)
    public void onButtonCommentClick()
    {
        Toast.makeText(this, "COMMENT!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.scenario_button_bug)
    public void onButtonBugClick()
    {
        Toast.makeText(this, "BUG!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.scenario_button_skip)
    public void onButtonSkipClick()
    {
        // TODO
        finish();
    }

    @OnClick(R.id.scenario_button_next_enabled)
    public void onButtonNextClick()
    {
        // TODO
        finish();
    }

    private void enableButtonNext(boolean enable)
    {
        if (enable)
        {
            buttonNextEnabled.setVisibility(View.VISIBLE);
            buttonNextDisabled.setVisibility(View.GONE);
        }
        else
        {
            buttonNextEnabled.setVisibility(View.GONE);
            buttonNextDisabled.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int layout()
    {
        return R.layout.screen_scenario;
    }
}