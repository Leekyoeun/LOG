package com.hongsup.explog.view.search;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.view.search.insuptest.SearchResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 정인섭 on 2017-12-13.
 */

public class SearchRecyclerResultAdapter extends RecyclerView.Adapter<SearchRecyclerResultAdapter.MyHolder> {
    ArrayList<PostCover> resultList = new ArrayList<>();

    public void resultNotifier(ArrayList<PostCover> list) {
        this.resultList = list;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_post, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textWriter.setText(resultList.get(position).getAuthor().getUsername());
        holder.textTitle.setText(resultList.get(position).getTitle());
        String date = resultList.get(position).getStartDate() + " ~ " + resultList.get(position).getEndDate();
        holder.textDate.setText(date);

    }

    @Override
    public int getItemCount() {
        Log.d("searchRechcleradapter", resultList.size() + "");
        return resultList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

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
        String date;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}