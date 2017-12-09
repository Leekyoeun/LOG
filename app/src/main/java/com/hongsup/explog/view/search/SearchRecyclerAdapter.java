package com.hongsup.explog.view.search;

import android.support.v7.widget.RecyclerView;
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

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.MyHolder>{

    private ArrayList<String> list = new ArrayList<>();
    ListAction listAction;

    public SearchRecyclerAdapter(ListAction listAction) {
        this.listAction = listAction;
    }

    public void notifier(ArrayList<String> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_search_recycler_history, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textView.setText(list.get(position));
        holder.word = list.get(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imgDeleteHistory;
        String word;

        public MyHolder(View itemView) {
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

    interface ListAction{
        void setHistoryToSearchEditText(String word);
        void deleteHistory(String word);
    }
}
