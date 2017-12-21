package com.hongsup.explog.view.myinfo.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.view.setting.editprofile.insuptest.Liked_posts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 정인섭 on 2017-12-01.
 */

public class ViewPagerAdapter extends PagerAdapter {

    RecyclerView recyclerView;
    RecyclerViewLikeAdapter recyclerViewLikeAdapter;
    RecyclerViewPostAdapter recyclerViewPostAdapter;
    ArrayList<List> list = new ArrayList<>();
    ArrayList<PostCover> postList;
    ArrayList<Liked_posts> liked_postsList;

    public ViewPagerAdapter(ArrayList<List> list) {
        this.list = list;
        postList = (ArrayList<PostCover>)list.get(0);
        liked_postsList = (ArrayList<Liked_posts>)list.get(1);
        Log.d("PostList Size", postList.size()+"");
    }

    public void setList(ArrayList<List> list){

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_myinfo_viewpager_itemlist, null);
        recyclerView = view.findViewById(R.id.myinfoRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recyclerViewLikeAdapter = new RecyclerViewLikeAdapter();
        recyclerViewPostAdapter = new RecyclerViewPostAdapter();
        recyclerViewPostAdapter.setList(postList);
        recyclerViewLikeAdapter.setList(liked_postsList);
        if(position==0){
            recyclerView.setAdapter(recyclerViewPostAdapter);
        }else{
            recyclerView.setAdapter(recyclerViewLikeAdapter);
        }


        container.addView(view);
        return view;
    }}
