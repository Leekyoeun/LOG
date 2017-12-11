package com.hongsup.explog.view.post.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hongsup.explog.view.post.listener.PostContentListener;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class PathViewHolder extends RecyclerView.ViewHolder {

    private int position;
    private PostContentListener postContentListener;
    private boolean checkMyPost;

    public PathViewHolder(View itemView, PostContentListener postContentListener, boolean checkMyPost) {
        super(itemView);
        this.postContentListener = postContentListener;
        this.checkMyPost = checkMyPost;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
