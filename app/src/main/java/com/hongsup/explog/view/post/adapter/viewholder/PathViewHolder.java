package com.hongsup.explog.view.post.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Content;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class PathViewHolder extends PostViewHolder {

    private static final String TAG = "PathViewHolder";

    @BindView(R.id.imgPath)
    ImageView imgPath;

    public PathViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Content data) {
        Glide.with(context)
                .load(createGoogleStaticMap(data.getLat(), data.getLng()))
                .into(imgPath);
    }

    private String createGoogleStaticMap(double lat, double lng) {
        String url = "http://maps.google.com/maps/api/staticmap?center=" + lat + "," + lng;
        // Zoom 속성
        url += "&zoom=17";
        //Size
        url += "&size=640x640";
        // Scale
        url += "&scale=2";
        // Map Type
        url += "&maptype=roadmap";
        // Marker
        url += "&markers=color:blue%7Clabel:L%7C" + lat + ", " + lng;

        return url;
    }
}
