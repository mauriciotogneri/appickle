package com.mauriciotogneri.appickle.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.adapters.ScenarioAdapter.ViewHolder;
import com.mauriciotogneri.appickle.base.BaseListAdapter;
import com.mauriciotogneri.appickle.base.BaseListAdapter.OnViewHolderClicked;
import com.mauriciotogneri.appickle.model.session.Scenario;

import java.util.List;

public class ScenarioAdapter extends BaseListAdapter<Scenario, ViewHolder> implements OnViewHolderClicked
{
    private OnItemSelected<Scenario> onItemSelected;

    public ScenarioAdapter(Context context, List<Scenario> scenarios, OnItemSelected<Scenario> onItemSelected)
    {
        super(context, R.layout.row_scenario, scenarios);

        this.onItemSelected = onItemSelected;
    }

    @Override
    protected ViewHolder viewHolder(View view)
    {
        return new ViewHolder(view, this);
    }

    @Override
    protected void fillView(ViewHolder viewHolder, Scenario scenario)
    {
        viewHolder.title.setText(scenario.name());
        viewHolder.description.setText(scenario.description());
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
        public final ImageView status;
        private final OnViewHolderClicked onViewHolderClicked;

        public ViewHolder(View view, OnViewHolderClicked onViewHolderClicked)
        {
            super(view);

            this.title = (TextView) view.findViewById(R.id.scenario_title);
            this.description = (TextView) view.findViewById(R.id.scenario_description);
            this.status = (ImageView) view.findViewById(R.id.scenario_status);

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