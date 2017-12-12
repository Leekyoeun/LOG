package com.hongsup.explog.view.post.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.view.post.listener.PostContentListener;

/**
 * Created by Android Hong on 2017-12-12.
 */

public class InitViewHolder extends PostViewHolder {

    private TextView textWriter;

    public InitViewHolder(View itemView) {
        super(itemView);
        textWriter = itemView.findViewById(R.id.textWriter);
    }

    @Override
    public void setListener(PostContentListener listener) {

    }

    @Override
    public void setPosition(int position) {

    }

    @Override
    public void bind(Content data) {
        textWriter.setText("안녕하세요. @이흥기!" );
    }
}
