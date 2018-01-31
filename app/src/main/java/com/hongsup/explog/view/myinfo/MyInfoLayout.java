package com.hongsup.explog.view.myinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.user.User;
import com.hongsup.explog.data.user.source.UserRepository;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.api.EditProfileAPI;
import com.hongsup.explog.view.custom.PostItemDivider;
import com.hongsup.explog.view.follow.FollowActivity;
import com.hongsup.explog.view.myinfo.adapter.RecyclerViewPostAdapter;
import com.hongsup.explog.view.setting.SettingActivity;
import com.hongsup.explog.view.setting.editprofile.insuptest.UserInformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by 정인섭 on 2017-12-06.
 */

public class MyInfoLayout extends FrameLayout {

    @BindView(R.id.imgSetting)
    ImageView imgSetting;
    @BindView(R.id.textUserNameMyInfo)
    TextView textUserNameMyInfo;
    @BindView(R.id.textEmailMyInfo)
    TextView textEmailMyInfo;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    @BindView(R.id.myinfo_recyclerview)
    RecyclerView myinfo_recyclerView;
    @BindView(R.id.textFollower)
    TextView textFollower;
    @BindView(R.id.textFollowing)
    TextView textFollowing;



    ArrayList<List> list;
    ArrayList<User> followerUserList;
    ArrayList<User> followingUserList;
    RecyclerViewPostAdapter recyclerViewPostAdapter;
    Context context;
    boolean[] result;

    public MyInfoLayout(Context context) {
        super(context);
        init();
        this.context = context;
    }


    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_myinfo, this, false);
        ButterKnife.bind(this, view);
        list = new ArrayList<>();
        recyclerViewPostAdapter = new RecyclerViewPostAdapter();
        setProfileFromUserRepository();
        getDataFromDB();
        myinfo_recyclerView.addItemDecoration(new PostItemDivider(48));

        setListener();

        addView(view);
    }

    private void setListener() {
        //tabLayoutClone.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutClone));
        imgSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingActivity.class);
                getContext().startActivity(intent);
            }
        });

        textFollower.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FollowActivity.class);
                intent.putExtra("follower", followerUserList);
                intent.putExtra("followerResult", checkIsFollowing());
                intent.setAction("follower");
                context.startActivity(intent);
                for(boolean result : result){
                    Log.d("MyInfoLayout", result + "");
                }
            }
        });

        textFollowing.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FollowActivity.class);
                intent.putExtra("following", followingUserList);
                intent.setAction("following");
                context.startActivity(intent);
            }
        });
    }

    //UserRepository로 부터 정보를 불러와 profile 완성
    public void setProfileFromUserRepository(){
        UserRepository userRepository = UserRepository.getInstance();
        if(userRepository.getUser()!=null) {
            ((Activity)getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("setProfileFromUserRepo", userRepository.getUser().getUsername());
                    Log.d("setProfileFromUserRepo", userRepository.getUser().getImg_profile());
                    Glide.with(getContext()).load(userRepository.getUser().getImg_profile()).centerCrop().into(imgProfile);
                    textUserNameMyInfo.setText(userRepository.getUser().getUsername());
                    textEmailMyInfo.setText(userRepository.getUser().getEmail());
                    getDataFromDB();

                }
            });

        }else{
            textUserNameMyInfo.setText("로그인 하쇼");
            textEmailMyInfo.setText("로그인 하쇼");
        }
    }

    public void getDataFromDB() {
        EditProfileAPI profileEditAPI = ServiceGenerator.createInterceptor(EditProfileAPI.class);
        Observable<Response<UserInformation>> getUserInfo = profileEditAPI.getUserInfo();
        getUserInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    if (data.isSuccessful()) {
                        if (data.code() == 200) {
                            Log.d("MyInfoLayout", "확인됨");

                            recyclerViewPostAdapter.setList(data.body().getPosts(), getContext());
                            myinfo_recyclerView.setAdapter(recyclerViewPostAdapter);
                            myinfo_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                            followerUserList = data.body().getFollowers();
                            followingUserList = data.body().getFollowing_users();
                            textFollowing.setText(followingUserList.size() + " Following");
                            textFollower.setText(followerUserList.size() + " Follower");
                        } else {
                            Log.d("EditProfileActivity", "확인안됨");
                        }
                    } else {
                        Log.d("EditProfileActivity", data.errorBody().string() + "data unsuccessful");
                    }
                }, throwable -> {
                    Log.e("SearchView", throwable.getMessage());
                });

    }

//    public void getFollowingDataFromDB(){
//        EditProfileAPI profileEditAPI = ServiceGenerator.createInterceptor(EditProfileAPI.class);
//        Observable<Response<UserInformation>> getUserInfo = profileEditAPI.getUserInfo();
//        getUserInfo.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(data -> {
//                    if (data.isSuccessful()) {
//                        if (data.code() == 200) {
//                            Log.d("MyInfoLayout", "확인됨");
//
//                            recyclerViewPostAdapter.setList(data.body().getPosts(), getContext());
//                            myinfo_recyclerView.setAdapter(recyclerViewPostAdapter);
//                            myinfo_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//                            followerUserList = data.body().getFollowers();
//                            followingUserList = data.body().getFollowing_users();
//                            textFollowing.setText(followingUserList.size() + " Following");
//                            textFollower.setText(followerUserList.size() + " Follower");
//
//                        } else {
//                            Log.d("EditProfileActivity", "확인안됨");
//                        }
//                    } else {
//                        Log.d("EditProfileActivity", data.errorBody().string() + "data unsuccessful");
//                    }
//                }, throwable -> {
//                    Log.e("SearchView", throwable.getMessage());
//                });
//
//    }

    public boolean[] checkIsFollowing(){
       result = new boolean[followerUserList.size()];
       Arrays.fill(result, false);
        for(int i=0; i<followerUserList.size(); i++){
            for(User followingUser : followingUserList){
                if(followerUserList.get(i).getPk()==followingUser.getPk()){
                    result[i]=true;
                    break;
                }else{
                    Log.d("MyInfoLayout", "뜨긴 뜨니??");
                    result[i]=false;
                }
            }
        }
        return result;
    }
}
