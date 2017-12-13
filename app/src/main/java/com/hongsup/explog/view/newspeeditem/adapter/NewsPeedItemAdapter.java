package com.hongsup.explog.view.newspeeditem.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.view.newspeeditem.adapter.contract.NewsPeedItemAdapterContract;
import com.hongsup.explog.view.newspeeditem.adapter.viewholder.NewsPeedItemViewHolder;
import com.hongsup.explog.view.newspeeditem.listener.OnCoverClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Hong on 2017-12-13.
 */

public class NewsPeedItemAdapter extends RecyclerView.Adapter<NewsPeedItemViewHolder> implements NewsPeedItemAdapterContract.iView, NewsPeedItemAdapterContract.iModel {

    private List<PostCover> postCoverList = new ArrayList<>();
    private OnCoverClickListener listener;

    @Override
    public NewsPeedItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new NewsPeedItemViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(NewsPeedItemViewHolder holder, int position) {
        PostCover postCover = postCoverList.get(position);

        if (holder != null){
            holder.setPostCover(postCover);
        }
    }

    @Override
    public int getItemCount() {
        return postCoverList.size();
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnItemClickListener(OnCoverClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void addItems(List<PostCover> postCoverList) {
        this.postCoverList = postCoverList;
    }
}
