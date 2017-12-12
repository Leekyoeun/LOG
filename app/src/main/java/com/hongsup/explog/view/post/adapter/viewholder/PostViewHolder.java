package com.hongsup.explog.view.post.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.view.post.listener.PostContentListener;

/**
 * Created by Android Hong on 2017-12-12.
 */

public abstract class PostViewHolder extends RecyclerView.ViewHolder {

    public PostViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setListener(PostContentListener listener);

    public abstract void setPosition(int position);

    public abstract void bind(Content data);
}
