package com.mauriciotogneri.appickle.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mauriciotogneri.appickle.R;

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration
{
    private Drawable divider;

    public SimpleDividerItemDecoration(Context context)
    {
        this.divider = ResourcesCompat.getDrawable(context.getResources(), R.drawable.line_divider, null);
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state)
    {
        int left = recyclerView.getPaddingLeft();
        int right = recyclerView.getWidth() - recyclerView.getPaddingRight();

        int childCount = recyclerView.getChildCount();

        for (int i = 0; i < childCount; i++)
        {
            View child = recyclerView.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();

            divider.setBounds(left, top, right, bottom);
            divider.draw(canvas);
        }
    }
}