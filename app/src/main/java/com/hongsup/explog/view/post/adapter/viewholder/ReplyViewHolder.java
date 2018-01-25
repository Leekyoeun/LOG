package com.hongsup.explog.view.post.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Content;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 정인섭 on 2018-01-24.
 */

public class ReplyViewHolder extends PostViewHolder {
    @BindView(R.id.txt_reply_content)
    TextView txt_reply_content;
    @BindView(R.id.txt_reply_date)
    TextView txt_reply_date;
    @BindView(R.id.txt_reply_user)
    TextView txt_reply_user;
    @BindView(R.id.img_reply_profile)
    ImageView img_reply_profile;

    public ReplyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void bind(Content data) {
        txt_reply_content.setText(data.getReply().getContent());
        txt_reply_user.setText(data.getReply().getAuthor().getUsername());
        txt_reply_date.setText(data.getReply().getCreated_at());
        Glide.with(context).load(data.getReply().getAuthor().getImg_profile()).into(img_reply_profile);
    }
}
