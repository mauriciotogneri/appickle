package com.mauriciotogneri.appickle.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mauriciotogneri.appickle.R;
import com.mauriciotogneri.appickle.adapters.FeatureAdapter.ViewHolder;
import com.mauriciotogneri.appickle.base.BaseListAdapter;
import com.mauriciotogneri.appickle.base.BaseListAdapter.OnViewHolderClicked;
import com.mauriciotogneri.appickle.model.session.Feature;

import java.util.List;

public class FeatureAdapter extends BaseListAdapter<Feature, ViewHolder> implements OnViewHolderClicked
{
    private OnItemSelected<Feature> onItemSelected;

    public FeatureAdapter(Context context, List<Feature> features, OnItemSelected<Feature> onItemSelected)
    {
        super(context, R.layout.row_feature, features);

        this.onItemSelected = onItemSelected;
    }

    @Override
    protected ViewHolder viewHolder(View view)
    {
        return new ViewHolder(view, this);
    }

    @Override
    protected void fillView(ViewHolder viewHolder, Feature feature)
    {
        viewHolder.title.setText(feature.name());
        viewHolder.description.setText(feature.description());

        switch (feature.status())
        {
            case PENDING:
                viewHolder.status.setImageResource(R.drawable.status_pending);
                break;

            case VALID:
                viewHolder.status.setImageResource(R.drawable.status_valid);
                break;

            case WARNING:
                viewHolder.status.setImageResource(R.drawable.status_warning);
                break;

            case ERROR:
                viewHolder.status.setImageResource(R.drawable.status_error);
                break;
        }
    }

    @Override
    public void onViewHolderClicked(int position)
    {
        onItemSelected.onItemSelected(item(position));
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

            this.title = (TextView) view.findViewById(R.id.feature_title);
            this.description = (TextView) view.findViewById(R.id.feature_description);
            this.status = (ImageView) view.findViewById(R.id.feature_status);

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