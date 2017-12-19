package com.hongsup.explog.view.post.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Content;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class TextViewHolder extends PostViewHolder {

    private TextView textContent;

    public TextViewHolder(View itemView) {
        super(itemView);
        textContent = itemView.findViewById(R.id.textContent);
    }

    @Override
    public void bind(Content data) {
        textContent.setText(data.getContent());
    }

}
