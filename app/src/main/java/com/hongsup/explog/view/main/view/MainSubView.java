package com.hongsup.explog.view.main.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hongsup.explog.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class MainSubView extends FrameLayout {

    private static final String TAG = "MainSubView";

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;

    public MainSubView(@NonNull Context context) {
        super(context);
        initView();
    }

    public MainSubView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_sub, null);
        ButterKnife.bind(this, view);
        // 로직 처리
        process();
        addView(view);
    }

    private void process() {

        List<String> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            data.add("Data : " + i);
        }

        CustomAdapter customAdapter = new CustomAdapter();
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        customAdapter.setData(data);

    }

    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        List<String> stringList = new ArrayList<>();

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String text = stringList.get(position);
            holder.setTextView(text);
        }

        public void setData(List<String> data) {
            this.stringList = data;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return stringList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }

            public void setTextView(String text) {
                textView.setText(text);
            }
        }
    }
}
