package com.hongsup.explog.view.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.view.newspeeditem.listener.OnCoverClickListener;
import com.hongsup.explog.view.search.adapter.contract.SearchResultAdapterContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 정인섭 on 2017-12-13.
 * <p>
 * Modified by Android Hong on 2017-12-21
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ResultHolder> implements SearchResultAdapterContract.iView, SearchResultAdapterContract.iModel {

    private List<PostCover> searchResultList;
    private Context context;
    private OnCoverClickListener listener;

    public SearchResultAdapter(Context context) {
        this.context = context;
        this.searchResultList = new ArrayList<>();
    }

    @Override
    public ResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_post, parent, false);
        return new ResultHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultHolder holder, int position) {
        holder.textWriter.setText(searchResultList.get(position).getAuthor().getUsername());
        holder.textTitle.setText(searchResultList.get(position).getTitle());

        String date = searchResultList.get(position).getStartDate() + " - " + searchResultList.get(position).getEndDate();
        holder.textDate.setText(date);

        Glide.with(context)
                .load(searchResultList.get(position).getCoverPath())
                .centerCrop()
                .into(holder.imageSearch);

        holder.setPostCover(searchResultList.get(position));
    }

    @Override
    public int getItemCount() {
        return searchResultList.size();
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnCoverClickListener(OnCoverClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void addItems(List<PostCover> searchResultList) {
        this.searchResultList.clear();
        this.searchResultList = searchResultList;
    }

    class ResultHolder extends RecyclerView.ViewHolder {

        //        @BindView(R.id.searchItemImageView)
        //        ImageView searchItemImageView;
        @BindView(R.id.textWriter)
        TextView textWriter;
        @BindView(R.id.textTitle)
        TextView textTitle;
        //        @BindView(R.id.textContent)
        //        TextView textContent;
        @BindView(R.id.textDate)
        TextView textDate;
        @BindView(R.id.textCalcDate)
        TextView textCalcDate;
        @BindView(R.id.textComment)
        TextView textComment;
        @BindView(R.id.textLike)
        TextView textLike;
        @BindView(R.id.imageSearch)
        ImageView imageSearch;

        private PostCover postCover;

        public ResultHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCoverClick(postCover);
                }
            });
        }

        private void setPostCover(PostCover cover){
            this.postCover = cover;
        }

    }
}