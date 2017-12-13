package com.hongsup.explog.view.newspeeditem.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.util.DateUtil;
import com.hongsup.explog.view.newspeeditem.listener.OnCoverClickListener;

/**
 * Created by Android Hong on 2017-12-13.
 */

public class NewsPeedItemViewHolder extends RecyclerView.ViewHolder {

    private PostCover postCover;
    private TextView textTitle;
    private ImageView imgCover;
    private TextView textDate;
    private TextView textWriter;
    private TextView textComment;
    private TextView textLike;

    public NewsPeedItemViewHolder(View itemView, OnCoverClickListener listener) {
        super(itemView);
        textTitle = itemView.findViewById(R.id.textTitle);
        imgCover = itemView.findViewById(R.id.imgCover);
        textDate = itemView.findViewById(R.id.textDate);
        textWriter = itemView.findViewById(R.id.textWriter);
        textComment = itemView.findViewById(R.id.textComment);
        textLike = itemView.findViewById(R.id.textLike);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCoverClick(postCover);
            }
        });
    }

    public void setPostCover(PostCover postCover) {
        this.postCover = postCover;

        textTitle.setText(postCover.getTitle());

        // Glide 를 사용하여
        // 배경 사진 뿌려줘야 한다.

        textDate.setText(DateUtil.getConvertDate(postCover.getStartDate(), postCover.getEndDate()));
        textWriter.setText(postCover.getAuthor().getUsername());
        textLike.setText(postCover.getLikeCount() +"");
    }
}