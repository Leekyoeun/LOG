package com.hongsup.explog.view.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongsup.explog.R;
import com.hongsup.explog.view.search.adapter.contract.HistorySearchAdapterContract;
import com.hongsup.explog.view.search.listener.OnWordClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 정인섭 on 2017-12-09.
 */

public class HistorySearchAdapter extends RecyclerView.Adapter<HistorySearchAdapter.HistoryHolder> implements HistorySearchAdapterContract.iView, HistorySearchAdapterContract.iModel {

    private Context context;
    private List<String> historySearchList;

    private OnWordClickListener listener;


    public HistorySearchAdapter(Context context) {
        this.context = context;
        this.historySearchList = new ArrayList<>();
    }

    @Override
    public HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_search_recycler_history, parent, false);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryHolder holder, int position) {
        holder.textViewWord.setText(historySearchList.get(position));
    }

    @Override
    public int getItemCount() {
        return historySearchList.size();
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnWordClickListener(OnWordClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void addItems(List<String> wordList) {
        this.historySearchList.clear();
        this.historySearchList = wordList;
    }

    class HistoryHolder extends ViewHolder {
        @BindView(R.id.textViewWord)
        TextView textViewWord;
        @BindView(R.id.imgDeleteHistory)
        ImageView imgDeleteHistory;

        public HistoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onWordClick(textViewWord.getText().toString());
                }
            });

            imgDeleteHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(textViewWord.getText().toString());
                }
            });
        }
    }

}