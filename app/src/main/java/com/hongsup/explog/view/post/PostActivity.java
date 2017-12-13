package com.hongsup.explog.view.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongsup.explog.R;
import com.hongsup.explog.data.Const;
import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.post.PostContent;
import com.hongsup.explog.view.post.adapter.PostAdapter;
import com.hongsup.explog.view.post.listener.PostContentListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostActivity extends AppCompatActivity implements PostContentListener {

    ImageView imgCover;
    TextView textTitle, textDate, textWriter;
    CircleImageView imgProfile;
    RecyclerView recyclerView;
    PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        initToolbar();
        initView();
        initAdapter();
        loadData();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        postAdapter = new PostAdapter(this, true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
    }

    private void loadData() {


        Intent intent = getIntent();
        PostCover post = (PostCover) intent.getSerializableExtra("POST");
/*
        if(post != null){
            // Cover 에서 작성한 경우
            textTitle.setText(post.getTitle());

            if(getResources().getString(R.string.txt_end_date).equals(post.getEndDate())){
                textDate.setText(post.getStartDate());
            }else{
                textDate.setText(post.getStartDate() + " - " + post.getEndDate());
            }
            postAdapter.setInitData();

        }else{
            // Post Item 을 클릭했을 경우
            // 데이터를 로드한다.
        }
*/

       /* if (post != null) {
            // Cover 작성한 후
            postAdapter.setInitData();
        }else{*/
            // Cover 작성이 아닌 Post Item 을 클릭했을 경우
            List<PostContent> postContentList = new ArrayList<>();
            for(int i = 0 ; i<100; i++){
                PostContent postContent;
                Content content;
                if(i % 3 == 0){
                    postContent = new PostContent();
                    postContent.setContentType(Const.CONTENT_TYPE_TEXT);
                    content = new Content();
                    content.setPk(i);
                    content.setContent("하이하이하이" + i);
                    postContent.setContent(content);
                    postContentList.add(postContent);
                }else if(i % 3 == 1){
                    postContent = new PostContent();
                    postContent.setContentType(Const.CONTENT_TYPE_PHOTO);
                    content = new Content();
                    content.setPk(i);
                    content.setPhotoPath("뿌잉뿌잉"  + i);
                    postContent.setContent(content);
                    postContentList.add(postContent);
                }else if(i % 3 == 2){
                    postContent = new PostContent();
                    postContent.setContentType(Const.CONTENT_TYPE_PATH);
                    content = new Content();
                    content.setPk(i);
                    content.setLng(i*2);
                    content.setLat(i*2);
                    postContent.setContent(content);
                    postContentList.add(postContent);
                }
            }
            postAdapter.setData(postContentList);
       // }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return true;
    }
}
