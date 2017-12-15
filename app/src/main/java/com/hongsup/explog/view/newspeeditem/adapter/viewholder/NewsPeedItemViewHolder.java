package com.hongsup.explog.view.newspeeditem.adapter.viewholder;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.util.DateUtil;
import com.hongsup.explog.view.newspeeditem.listener.OnCoverClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Hong on 2017-12-13.
 */

public class NewsPeedItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.imgCover)
    ImageView imgCover;
    @BindView(R.id.textDate)
    TextView textDate;
    @BindView(R.id.textWriter)
    TextView textWriter;
    @BindView(R.id.textComment)
    TextView textComment;
    @BindView(R.id.textLike)
    TextView textLike;

    private PostCover postCover;
    private Context context;

    public NewsPeedItemViewHolder(View itemView, OnCoverClickListener listener) {
        super(itemView);
        this.context = itemView.getContext();
        ButterKnife.bind(this, itemView);

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
        Glide.with(context)
                .load(postCover.getCoverPath())
                .centerCrop()
                .into(imgCover);
        imgCover.setColorFilter(ContextCompat.getColor(context, R.color.colorMainTint), PorterDuff.Mode.SRC_OVER);

        textDate.setText(DateUtil.getConvertDate(postCover.getStartDate(), postCover.getEndDate()));
        textWriter.setText(postCover.getAuthor().getUsername());
        textLike.setText(postCover.getLikeCount() + "");
    }
}