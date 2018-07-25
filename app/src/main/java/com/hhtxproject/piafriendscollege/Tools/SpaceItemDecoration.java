package com.hhtxproject.piafriendscollege.Tools;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 轩、 on 2018/3/24.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{
    private int leftRight;
    private int topBottom;
    private int spacing;

    /**
     *
     * @param leftRight
     * @param topBottom
     * @param spacing
     */
    public SpaceItemDecoration(int leftRight, int topBottom ,int spacing) {
        this.leftRight = leftRight;
        this.topBottom = topBottom;
        this.spacing = spacing;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        //竖直方向的
        if (layoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
            //最后一项需要 bottom
            if (parent.getChildAdapterPosition(view) == layoutManager.getItemCount() - 1) {
                outRect.bottom = topBottom;
            }
            outRect.top = topBottom;
            outRect.left = leftRight;
            outRect.right = leftRight;
        } else {
            //最后一项需要right
            if (parent.getChildAdapterPosition(view) == layoutManager.getItemCount() - 1) {
                outRect.right = leftRight;
            }
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = leftRight;
            }
        }
    }


}

