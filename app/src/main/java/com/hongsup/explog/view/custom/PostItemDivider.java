package com.hongsup.explog.view.custom;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Android Hong on 2017-12-07.
 */

public class PostItemDivider extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;

    public PostItemDivider(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.right = verticalSpaceHeight;
        outRect.left= verticalSpaceHeight;
        outRect.bottom = verticalSpaceHeight;
        if (parent.getChildAdapterPosition(view) == 0 ) {
            // 첫번째 아이템만
            outRect.top = verticalSpaceHeight;
        }
    }
}