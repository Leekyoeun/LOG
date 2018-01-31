package com.hongsup.explog.view.follow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hongsup.explog.R;
import com.hongsup.explog.data.user.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowActivity extends AppCompatActivity {

    ArrayList<User> list = new ArrayList<>();
    FollowRecyclerViewAdapter followRecyclerViewAdapter;
    @BindView(R.id.followRecyclerView)
    RecyclerView followRecyclerView;
    boolean[] result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);
        ButterKnife.bind(this);
        followRecyclerViewAdapter = new FollowRecyclerViewAdapter();
        Log.d("FollowActivity", list.size() + "");
        getIntentData();
    }

    public void getIntentData(){
        switch (getIntent().getAction()){
            case "following" :
                list = (ArrayList) getIntent().getSerializableExtra("following");
                followRecyclerViewAdapter.setList(list, getBaseContext());
                break;
            case "follower" :
                list = (ArrayList) getIntent().getSerializableExtra("follower");
                result = getIntent().getBooleanArrayExtra("followerResult");
                followRecyclerViewAdapter.setList(list, getBaseContext(), result);
                break;
        }

        followRecyclerView.setAdapter(followRecyclerViewAdapter);
        followRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

}
