package com.hongsup.explog.view.search;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongsup.explog.R;

import java.util.ArrayList;

/**
 * Created by 정인섭 on 2017-12-09.
 */

public class SearchRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final static int HISTORY = 111;
    private final static int RESULT = 222;
    private final static int NOTHINGTOSEARCH = 333;
    private int flag = HISTORY;

    private ArrayList<String> historyList = new ArrayList<>();
    //private ArrayList<Result> resultList = new ArrayList<>();
    private  ListAction listAction;

    public SearchRecyclerAdapter(ListAction listAction) {
        this.listAction = listAction;
    }

    public void historyNotifier(ArrayList<String> historyList) {
        this.historyList = historyList;
        notifyDataSetChanged();
        flag = HISTORY;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (flag) {

            case HISTORY:
                View historyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_search_recycler_history, parent, false);
                return new HistoryHolder(historyView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (flag) {
            case HISTORY:
                ((HistoryHolder) holder).textView.setText(historyList.get(position));
                ((HistoryHolder) holder).word = historyList.get(position);
                break;
            case RESULT :
        }
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    class HistoryHolder extends ViewHolder {
        TextView textView;
        ImageView imgDeleteHistory;
        String word;

        public HistoryHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewWord);
            imgDeleteHistory = itemView.findViewById(R.id.imgDeleteHistory);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listAction.setHistoryToSearchEditText(word);
                }
            });

            imgDeleteHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listAction.deleteHistory(word);
                }
            });
        }
    }

    interface ListAction {
        void setHistoryToSearchEditText(String word);

        void deleteHistory(String word);
    }
}