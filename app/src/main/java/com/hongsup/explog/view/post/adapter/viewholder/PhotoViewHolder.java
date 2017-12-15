package com.hongsup.explog.view.post.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Content;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class PhotoViewHolder extends PostViewHolder {

    private ImageView imgPhoto;

    public PhotoViewHolder(View itemView) {
        super(itemView);
        imgPhoto = itemView.findViewById(R.id.imgPhoto);
    }

    @Override
    public void bind(Content data) {
        Glide.with(context)
                .load(R.drawable.main)
                .into(imgPhoto);

    }
}
