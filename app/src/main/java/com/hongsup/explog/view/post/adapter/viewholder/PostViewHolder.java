package com.hongsup.explog.view.post.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.view.post.listener.OnPostContentClickListener;

/**
 * Created by Android Hong on 2017-12-12.
 */

public abstract class PostViewHolder extends RecyclerView.ViewHolder {

    int position;
    Context context;
    boolean checkMyPost;
    OnPostContentClickListener listener;

    public PostViewHolder(View itemView) {
        super(itemView);
    }

    public void setCheckMyPost(boolean checkMyPost) {
        this.checkMyPost = checkMyPost;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setListener(OnPostContentClickListener listener){
        this.listener = listener;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public abstract void bind(Content data);
}
