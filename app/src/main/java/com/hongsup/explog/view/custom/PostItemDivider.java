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
        // 마지막 아이템이 아닌 경우, 공백 추가
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = verticalSpaceHeight;
        }
    }
}