package com.hongsup.explog.view.myinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.Const;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.user.source.UserRepository;
import com.hongsup.explog.view.post.PostActivity;
import com.hongsup.explog.view.setting.editprofile.insuptest.Posts;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 정인섭 on 2017-12-05.
 */

public class RecyclerViewPostAdapter extends RecyclerView.Adapter<RecyclerViewPostAdapter.MyHolder> {
    ArrayList<Posts> list = new ArrayList<>();
    UserRepository userRepository = UserRepository.getInstance();
    Context context;

    public void setList(ArrayList<Posts> list, Context context){
        this.list = list;
        notifyDataSetChanged();
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textTitle.setText(list.get(position).getTitle());
        String date = list.get(position).getStart_date() + " - " + list.get(position).getEnd_date();
        holder.textDate.setText(date);
        holder.posts = list.get(position);
        //holder.posts.setAuthor(userRepository.getUser());
        holder.textLike.setText(list.get(position).getNum_liked()+"");

        Glide.with(context).load(list.get(position).getImg()).centerCrop().into(holder.imgCover);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textTitle)
        TextView textTitle;
        @BindView(R.id.textDate)
        TextView textDate;
        @BindView(R.id.textWriter)
        TextView textWriter;
        @BindView(R.id.imgCover)
        ImageView imgCover;
        @BindView(R.id.textLike)
        TextView textLike;


        Posts posts;

        public MyHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), PostActivity.class);
                    PostCover postCover = new PostCover();
                    postCover.makePostCover(posts);
                    postCover.setAuthor(userRepository.getUser());


                    intent.putExtra(Const.INTENT_EXTRA_COVER, postCover);
                    itemView.getContext().startActivity(intent);
                }
            });
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
