package com.hongsup.explog.view.newspeed.view;

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
import com.hongsup.explog.view.custom.PostItemDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class NewsPeedItemView extends FrameLayout {

    private static final String TAG = "MainSubView";

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;

    public NewsPeedItemView(@NonNull Context context) {
        super(context);
        initView();
    }

    public NewsPeedItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_newspeed_content, null);
        ButterKnife.bind(this, view);
        // 로직 처리
        initAdapter();

        addView(view);
    }

    private void initAdapter() {

        List<String> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            data.add("Data : " + i);
        }

        CustomAdapter customAdapter = new CustomAdapter();
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        /*
        RecyclerView 사이에 여백 주는 Code
         */
        recyclerView.addItemDecoration(new PostItemDivider(48));
        customAdapter.setData(data);
    }

    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        List<String> stringList = new ArrayList<>();

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String text = stringList.get(position);
            if(holder != null)
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
                textView = itemView.findViewById(R.id.textTitle);
            }

            public void setTextView(String text) {
                textView.setText(text);
            }

        }


    }
}
