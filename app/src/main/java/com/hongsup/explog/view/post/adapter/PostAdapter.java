package com.hongsup.explog.view.post.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongsup.explog.R;
import com.hongsup.explog.data.Const;
import com.hongsup.explog.data.post.PostContent;
import com.hongsup.explog.view.post.adapter.viewholder.TextViewHolder;
import com.hongsup.explog.view.post.listener.PostContentListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class PostAdapter extends RecyclerView.Adapter {

    private static final String TAG = "PostAdapter";

    private List<PostContent> postContentList;
    private PostContentListener postContentListener;
    private boolean checkMyPost;

    public PostAdapter(Context context, boolean checkMyPost) {
        postContentList = new ArrayList<>();
        this.postContentListener = (PostContentListener) context;
        this.checkMyPost = checkMyPost;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Const.VIEW_TYPE_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_text, parent, false);
                return new TextViewHolder(view, postContentListener, checkMyPost);
            case Const.VIEW_TYPE_PHOTO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_photo, parent, false);
                return new TextViewHolder(view, postContentListener, checkMyPost);
            case Const.VIEW_TYPE_PATH:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_path, parent, false);
                return new TextViewHolder(view, postContentListener, checkMyPost);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PostContent postContent = postContentList.get(position);
        /*if (Const.CONTENT_TYPE_TEXT.equals(postContent.getContentType())) {
            ((TextViewHolder) holder).setPosition(position);
            ((TextViewHolder) holder).setText();
        } else if (Const.CONTENT_TYPE_PHOTO.equals(postContent.getContentType())) {
            ((PhotoViewHolder) holder).setPosition(position);
            ((PhotoViewHolder) holder).setPhoto();
        } else if (Const.CONTENT_TYPE_PATH.equals(postContent.getContentType())) {
            ((PathViewHolder) holder).setPosition(position);
            ((PathViewHolder) holder).setPath();
        }
        */
        /*if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).setPosition(position);
            ((TextViewHolder) holder).setText();
        } else if (holder instanceof PhotoViewHolder) {
            ((PhotoViewHolder) holder).setPosition(position);
            ((TextViewHolder) holder).setPhoto()
        } else if (holder instanceof PathViewHolder) {
            ((PathViewHolder) holder).setPosition(position);
            ((TextViewHolder) holder).setPath()
        }*/
    }

    @Override
    public int getItemCount() {
        return postContentList.size();
    }

    @Override
    public int getItemViewType(int position) {
        PostContent postContent = postContentList.get(position);
        Log.e(TAG, "getItemViewType.getType() : " + postContent.getContentType());

        if (Const.CONTENT_TYPE_TEXT.equals(postContent.getContentType())) {
            return Const.VIEW_TYPE_TEXT;
        } else if (Const.CONTENT_TYPE_PHOTO.equals(postContent.getContentType())) {
            return Const.VIEW_TYPE_PHOTO;
        } else if (Const.CONTENT_TYPE_PATH.equals(postContent.getContentType())) {
            return Const.VIEW_TYPE_PATH;
        }
        throw new RuntimeException("there is no type that matches the type " + postContent.getContentType() + " + make sure your using types correctly");
    }

    public void setData(List<PostContent> postContentList) {
        this.postContentList = postContentList;
        notifyDataSetChanged();
    }

    public void addContent(PostContent postContent) {
        postContentList.add(postContent);
        notifyItemInserted(postContentList.size() - 1);
    }

    public void deleteContent(int position) {
        postContentList.remove(position);
        notifyItemRemoved(position);
    }


}
