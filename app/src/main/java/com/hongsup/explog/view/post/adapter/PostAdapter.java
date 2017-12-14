package com.hongsup.explog.view.post.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongsup.explog.data.Const;
import com.hongsup.explog.data.post.PostContent;
import com.hongsup.explog.view.post.adapter.contract.PostAdapterContract;
import com.hongsup.explog.view.post.adapter.viewholder.PostViewHolder;
import com.hongsup.explog.view.post.listener.OnPostContentClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> implements PostAdapterContract.iModel, PostAdapterContract.iView {

    private static final String TAG = "PostAdapter";

    private Context context;
    private List<PostContent> postContentList;
    private OnPostContentClickListener listener;
    private boolean checkMyPost;

    public PostAdapter(Context context, boolean checkMyPost) {
        postContentList = new ArrayList<>();
        this.context = context;
        this.checkMyPost = checkMyPost;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = PostViewHolderFactory.getPostItemLayoutId(viewType);
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return PostViewHolderFactory.getViewHolder(viewType, view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        PostContent postContent = postContentList.get(position);
        holder.setContext(context);
        holder.setPosition(position);
        holder.setListener(listener);
        holder.setCheckMyPost(checkMyPost);
        holder.bind(postContent.getContent());
    }

    @Override
    public int getItemCount() {
        return postContentList.size();
    }

    @Override
    public int getItemViewType(int position) {
        PostContent postContent = postContentList.get(position);
        if (Const.CONTENT_TYPE_TEXT.equals(postContent.getContentType())) {
            return Const.VIEW_TYPE_TEXT;
        } else if (Const.CONTENT_TYPE_PHOTO.equals(postContent.getContentType())) {
            return Const.VIEW_TYPE_PHOTO;
        } else if (Const.CONTENT_TYPE_PATH.equals(postContent.getContentType())) {
            return Const.VIEW_TYPE_PATH;
        }else if(Const.CONTENT_TYPE_INIT.equals(postContent.getContentType())){
            return Const.VIEW_TYPE_INIT;
        }
        throw new RuntimeException("there is no type that matches the type " + postContent.getContentType() + " + make sure your using types correctly");
    }

/*

    public void addContent(PostContent postContent) {
        if(Const.CONTENT_TYPE_INIT.equals(postContentList.get(0).getContentType())){
            this.postContentList.clear();
        }

        this.postContentList.add(postContent);
        notifyItemInserted(postContentList.size() - 1);
    }

    public void deleteContent(int position) {
        postContentList.remove(position);
        notifyItemRemoved(position);

        if(postContentList.size() == 0){
            setInit();
            notifyAdapter();
        }
    }
*/

    @Override
    public void notifyAdapter() {
        notifyItemRangeChanged(0,postContentList.size());
    }

    @Override
    public void setOnPostContentClickListener(OnPostContentClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void setInit() {
        PostContent postContent = new PostContent();
        postContent.setContentType("init");
        postContentList.add(postContent);
    }

    @Override
    public void addItems(List<PostContent> postContentList) {
        this.postContentList.clear();
        this.postContentList = postContentList;
    }
}
