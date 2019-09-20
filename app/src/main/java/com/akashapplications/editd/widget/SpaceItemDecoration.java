package com.akashapplications.editd.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by huangwei on 2018/9/9.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public SpaceItemDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();
        if (pos == 0) {
            outRect.left = mSpace;
        }
        outRect.right = mSpace;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

    }
}
