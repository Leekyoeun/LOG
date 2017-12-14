package com.hongsup.explog.view.post.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.view.post.listener.OnPostContentClickListener;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class TextViewHolder extends PostViewHolder {

    private int position;
    private OnPostContentClickListener listener;

    private TextView textContent;

    private Context context;

    public TextViewHolder(View itemView) {
        super(itemView);
        textContent = itemView.findViewById(R.id.textContent);
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
        textContent.setText(data.getContent());
    }

}
