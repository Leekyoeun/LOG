package com.hongsup.explog.view.follow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Following;
import com.hongsup.explog.data.user.User;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.api.PostAPI;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by 정인섭 on 2018-01-22.
 */

public class FollowRecyclerViewAdapter extends RecyclerView.Adapter<FollowRecyclerViewAdapter.MyHolder> {
    ArrayList<User> list;
    Context context;
    boolean result[];

    public void setList(ArrayList<User> list, Context context){
        this.list = list;
        this.context = context;
        result = new boolean[list.size()];
        Arrays.fill(result, true);
    }

    public void setList(ArrayList<User> list, Context context, boolean result[]){
        this.list = list;
        this.context = context;
        this.result = result;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_footer, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textAuthor.setText(list.get(position).getUsername());
        holder.textEmail.setText(list.get(position).getEmail());
        Glide.with(context).load(list.get(position).getImg_profile()).centerCrop().into(holder.imgProfile);
        holder.userPk = list.get(position).getPk();
        if(result[position]){
            holder.textFollow.setText("Unfollow");
        }else{
            holder.textFollow.setText("Follow");
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgProfile)
        ImageView imgProfile;
        @BindView(R.id.textAuthor)
        TextView textAuthor;
        @BindView(R.id.textEmail)
        TextView textEmail;
        @BindView(R.id.textFollow)
        TextView textFollow;
        @BindView(R.id.likeLayout)
        LinearLayout likeLayout;
        int userPk;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            likeLayout.setVisibility(View.GONE);
            textFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setFollowFromDB(userPk);
                    if(textFollow.getText().equals("Unfollow")){
                        textFollow.setText("Follow");
                    }else{
                        textFollow.setText("Unfollow");
                    }

                }
            });
        }

        public void setFollowFromDB(int userPk){
            Following follow = new Following();
            follow.setTo_user(String.valueOf(userPk));
            PostAPI postApi = ServiceGenerator.createInterceptor(PostAPI.class);
            Observable<Response<Following>> following = postApi.following(follow);
            following.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data->{
                        if(data.isSuccessful()){
                            Log.d("FollowClick", "눌리니?" + "   " + userPk);
                            //Log.d("FollowClick", data.body().getFrom_user());

                        }else{
                            Log.d("FollowClick", "실패?" + "   " + userPk);
                            //Log.d("FollowClick", data.body().getFrom_user() + " " + data.body().getTo_user());
                            Log.d("FollowClick", data.errorBody().string());
                        }
                    }, throwable -> {
                        Log.e(TAG, "FollowClick: " + throwable.getMessage());
                    });
        }
    }
}
