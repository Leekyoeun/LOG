package com.hongsup.explog.view.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.Post;
import com.hongsup.explog.view.post.adapter.PostAdapter;
import com.hongsup.explog.view.post.listener.PostContentListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostActivity extends AppCompatActivity implements PostContentListener{

    ImageView imgCover;
    TextView textTitle, textDate, textWriter;
    CircleImageView imgProfile;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        initToolbar();
        initView();
        initAdapter();
        setData();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        imgCover = findViewById(R.id.imgCover);
        textTitle = findViewById(R.id.textTitle);
        textDate = findViewById(R.id.textDate);
        textWriter = findViewById(R.id.textWriter);
        imgProfile = findViewById(R.id.imgProfile);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initAdapter() {
        PostAdapter postAdapter = new PostAdapter(this, true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
    }

    private void setData() {

        Intent intent = getIntent();
        Post post = (Post) intent.getSerializableExtra("POST");

        if(post.getPostContentList() == null || post.getPostContentList().size() == 0){

        }

        /**
         * 데이터를 Adapter 에 넘겨준다.
         */

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return true;
    }


}
