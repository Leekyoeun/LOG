package com.hongsup.explog.view.post.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.view.post.listener.PostContentListener;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class PhotoViewHolder extends PostViewHolder {

    private int position;
    private PostContentListener postContentListener;

    private TextView textPhoto;

    public PhotoViewHolder(View itemView) {
        super(itemView);
        textPhoto = itemView.findViewById(R.id.textPhoto);
    }

    @Override
    public void setListener(PostContentListener listener) {
        this.postContentListener = listener;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void bind(Content data) {
        String photoText = data.getPhotoPath();
        textPhoto.setText(photoText);
    }
}
