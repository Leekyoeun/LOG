package com.hongsup.explog.view.myinfo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.hongsup.explog.R;
import com.hongsup.explog.view.myinfo.recyclerview.RecyclerViewAdapter;

/**
 * Created by 정인섭 on 2017-12-06.
 */

public class MyInfoLayout extends FrameLayout {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    public MyInfoLayout(Context context) {
        super(context);

        init();
    }


    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.myinfo_view, this, false);
        recyclerView = view.findViewById(R.id.myinfoRecyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        addView(view);
    }

}
