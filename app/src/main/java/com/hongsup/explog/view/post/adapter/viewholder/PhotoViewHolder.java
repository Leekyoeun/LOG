package com.hongsup.explog.view.post.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.view.post.listener.OnPostContentClickListener;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class PhotoViewHolder extends PostViewHolder {

    private int position;
    private OnPostContentClickListener listener;

    private ImageView imgPhoto;

    private Context context;

    public PhotoViewHolder(View itemView) {
        super(itemView);
        imgPhoto = itemView.findViewById(R.id.imgPhoto);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void setListener(OnPostContentClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void setCheckMyPost(boolean checkMyPost) {

    }

    @Override
    public void bind(Content data) {
        Glide.with(context)
                .load(R.drawable.main)
                .into(imgPhoto);

    }
}
