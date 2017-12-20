package com.hongsup.explog.view.post.adapter.viewholder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.data.user.source.UserRepository;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Android Hong on 2017-12-12.
 */

public class InitViewHolder extends PostViewHolder {

    private boolean onFollowing;

    @BindView(R.id.textWriter)
    TextView textWriter;
    @BindView(R.id.textSummary)
    TextView textSummary;

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

    public InitViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Content data) {

        if (onFollowing) {

        } else {

        }

        /**
         *  좋아요 설정
         */
        setLiked(data.getLiked(), data.getLikeCount());

        if (checkMyPost) {
            // 내 글이면
            textWriter.setText("안녕하세요. " + UserRepository.getInstance().getUser().getUsername() + " 님!");
            textSummary.setText("당신의 여행 이야기를 작성해보세요");

            lineView.setVisibility(View.GONE);
            authorLayout.setVisibility(View.GONE);
        } else {
            // 남 글이면
            textWriter.setText("음... 아직 작성이 안되었네요!");
            textSummary.setText("다음 기회에 여행 이야기를 둘러보세요");

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
    public void onLikeClick() {

        postLikeClickListener.setOnLikeClick(position);
    }

    @OnClick(R.id.textFollow)
    public void onFollowClick() {
        if (onFollowing) {

        } else {

        }
    }

    private void setLiked(int[] liked, int likeCount) {
        /**
         *  좋아요 설정
         */
        if (UserRepository.getInstance().getUser() != null && isLiked(liked)) {
            // '좋아요'를 눌렀을 경우
            imgLike.setImageResource(R.drawable.ic_like_red_16dp);
            textLikeCount.setTextColor(context.getResources().getColor(R.color.colorRed));
            textLikeCount.setText(likeCount + "");
        } else {
            // '좋아요'를 누르지 않았을 경우
            imgLike.setImageResource(R.drawable.ic_like_gray_16dp);
            textLikeCount.setTextColor(context.getResources().getColor(R.color.colorGray));
            textLikeCount.setText(likeCount + "");
        }
    }

    private boolean isLiked(int[] liked) {
        if (liked != null && Arrays.binarySearch(liked, UserRepository.getInstance().getUser().getPk()) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
