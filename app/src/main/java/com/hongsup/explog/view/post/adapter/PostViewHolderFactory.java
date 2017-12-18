package com.hongsup.explog.view.post.adapter;

import android.view.View;

import com.hongsup.explog.R;
import com.hongsup.explog.data.Const;
import com.hongsup.explog.view.post.adapter.viewholder.FooterViewHolder;
import com.hongsup.explog.view.post.adapter.viewholder.InitViewHolder;
import com.hongsup.explog.view.post.adapter.viewholder.PathViewHolder;
import com.hongsup.explog.view.post.adapter.viewholder.PhotoViewHolder;
import com.hongsup.explog.view.post.adapter.viewholder.PostViewHolder;
import com.hongsup.explog.view.post.adapter.viewholder.TextViewHolder;

/**
 * Created by Android Hong on 2017-12-12.
 */

public class PostViewHolderFactory {
    /**
     * Type 에 맞게 Layout Id 를 반환하는 메소드
     *
     * @param type
     * @return
     */
    public static int getPostItemLayoutId(int type) {
        int layoutId;

        switch (type) {
            case Const.VIEW_TYPE_INIT:
                layoutId = R.layout.item_post_init;
                break;
            case Const.VIEW_TYPE_TEXT:
                layoutId = R.layout.item_post_text;
                break;
            case Const.VIEW_TYPE_PHOTO:
                layoutId = R.layout.item_post_photo;
                break;
            case Const.VIEW_TYPE_PATH:
                layoutId = R.layout.item_post_path;
                break;
            case Const.VIEW_TYPE_FOOTER:
                layoutId = R.layout.item_post_footer;
                break;
            default:
                throw new RuntimeException(type + "에 맞는 Layout 이 없습니다.");
        }
        return layoutId;
    }

    public static PostViewHolder getViewHolder(int type, View itemView) {

        PostViewHolder viewHolder;
        switch (type) {
            case Const.VIEW_TYPE_INIT:
                viewHolder = new InitViewHolder(itemView);
                break;
            case Const.VIEW_TYPE_TEXT:
                viewHolder = new TextViewHolder(itemView);
                break;
            case Const.VIEW_TYPE_PHOTO:
                viewHolder = new PhotoViewHolder(itemView);
                break;
            case Const.VIEW_TYPE_PATH:
                viewHolder = new PathViewHolder(itemView);
                break;
            case Const.VIEW_TYPE_FOOTER:
                viewHolder = new FooterViewHolder(itemView);
                break;
            default:
                throw new RuntimeException(type + "에 맞는 ViewHolder 가 없습니다.");
        }
        return viewHolder;
    }
}
