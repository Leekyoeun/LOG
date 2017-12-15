package com.hongsup.explog.view.post.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Content;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class PathViewHolder extends PostViewHolder {

    private ImageView imgPath;

    public PathViewHolder(View itemView) {
        super(itemView);
        imgPath = itemView.findViewById(R.id.imgPhoto);
    }

    @Override
    public void bind(Content data) {
        String path = data.getLat() +" . " + data.getLng();
        imgPath.setBackgroundResource(R.drawable.ic_home);
    }
}
