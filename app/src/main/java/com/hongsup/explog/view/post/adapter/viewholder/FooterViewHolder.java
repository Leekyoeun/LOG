package com.hongsup.explog.view.post.adapter.viewholder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Content;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Android Hong on 2017-12-15.
 */

public class FooterViewHolder extends PostViewHolder {

    private boolean onFollowing;


    @BindView(R.id.lineView)
    View lineView;
    @BindView(R.id.authorLayout)
    RelativeLayout authorLayout;
    @BindView(R.id.textAuthor)
    TextView textAuthor;
    @BindView(R.id.textEmail)
    TextView textEmail;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    @BindView(R.id.imgLike)
    ImageButton imgLike;
    @BindView(R.id.textLikeCount)
    TextView textLikeCount;


    public FooterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Content data) {
        if(onFollowing){

        }else{

        }

        textLikeCount.setText(data.getLikeCount() + "");

        if (checkMyPost) {
            // 내 글이면
            lineView.setVisibility(View.GONE);
            authorLayout.setVisibility(View.GONE);
        } else {
            // 남 글이면
            lineView.setVisibility(View.VISIBLE);
            authorLayout.setVisibility(View.VISIBLE);

            textAuthor.setText(data.getAuthor().getUsername());
            textEmail.setText(data.getAuthor().getEmail());

            Glide.with(context)
                    .load(data.getAuthor().getImg_profile())
                    .into(imgProfile);
        }
    }

    @OnClick(R.id.imgLike)
    public void onLikeClick(){

    }

    @OnClick(R.id.textFollow)
    public void onFollowClick(){
        if(onFollowing){

        }else{

        }
    }
}
