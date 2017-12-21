package com.hongsup.explog.view.myinfo.adapter;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hongsup.explog.R;
import com.hongsup.explog.view.setting.editprofile.insuptest.Liked_posts;
import com.hongsup.explog.view.setting.editprofile.insuptest.Posts;

import java.util.ArrayList;

/**
 * Created by 정인섭 on 2017-12-05.
 */

public class RecyclerViewLikeAdapter extends RecyclerView.Adapter<RecyclerViewLikeAdapter.MyHolder> {

    ArrayList<Liked_posts> list = new ArrayList<>();

    public void setList(ArrayList<Liked_posts> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        view.setBackgroundColor(Color.YELLOW);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return list.size();
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
