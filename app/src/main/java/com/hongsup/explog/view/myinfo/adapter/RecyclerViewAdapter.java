package com.hongsup.explog.view.myinfo.adapter;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hongsup.explog.R;

/**
 * Created by 정인섭 on 2017-12-05.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {
    public final static int HEADER = 99;
    public final static int CONTENT = 77;

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch(viewType){

            case CONTENT :
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_myinfo_recycler_itemlist, parent, false);
                break;
        }
        
        return new MyHolder(view);
    }

    @Override
    public int getItemViewType(int position) {

            return CONTENT;

    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TabLayout tabLayout;
        Button button;

        public MyHolder(final View itemView) {
            super(itemView);

//            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//                @Override
//                public void onTabSelected(TabLayout.Tab tab) {
//                    switch(tab.getPosition()){
//                        case 0 :
//                            Toast.makeText(itemView.getContext(), "첫번째", Toast.LENGTH_SHORT).show();
//                            break;
//
//                        case 1 :
//                            Toast.makeText(itemView.getContext(), "두번째", Toast.LENGTH_SHORT).show();
//                            break;
//                    }
//                }
//
//                @Override
//                public void onTabUnselected(TabLayout.Tab tab) {
//
//                }
//
//                @Override
//                public void onTabReselected(TabLayout.Tab tab) {
//
//                }
//            });
        }
    }
}
