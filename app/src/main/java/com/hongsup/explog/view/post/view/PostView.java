package com.hongsup.explog.view.post.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.Const;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.user.source.UserRepository;
import com.hongsup.explog.util.DateUtil;
import com.hongsup.explog.view.post.adapter.PostAdapter;
import com.hongsup.explog.view.post.contract.PostContract;
import com.hongsup.explog.view.posttext.PostTextActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Android Hong on 2017-12-14.
 */

public class PostView implements PostContract.iView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgCover)
    ImageView imgCover;
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.textDate)
    TextView textDate;
    @BindView(R.id.textWriter)
    TextView textWriter;
    @BindView(R.id.imgProfile)
    CircleImageView imgProfile;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.progressBarLayout)
    RelativeLayout progressBarLayout;

    private Context context;
    private PostContract.iPresenter presenter;
    private View view;
    private Intent coverIntent;
    private Intent textIntent;
    private PostCover cover;
    private PostAdapter postAdapter;
    private int menuId;


    public PostView(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.activity_post, null);
        ButterKnife.bind(this, view);

        coverIntent = ((Activity) context).getIntent();
        cover = (PostCover) coverIntent.getSerializableExtra(Const.INTENT_EXTRA_COVER);

        initToolbar();
        initAdapter();
        setCoverData();
    }

    @Override
    public void setPresenter(PostContract.iPresenter presenter) {
        this.presenter = presenter;
        this.presenter.setPostAdapterModel(postAdapter);
        this.presenter.setPostAdapterView(postAdapter);
        this.presenter.loadPostContent(cover.getPk());
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void showProgress() {
        progressBarLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBarLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

    }

    @Override
    public void setMenu(Menu menu) {
        if (UserRepository.getInstance().getUser() != null && cover.getAuthor().getEmail().equals(UserRepository.getInstance().getUser().getEmail())) {
            // 내 글인경우
            menuId = R.menu.menu_my_post;
        } else {
            // 남의 글인경우
            menuId = R.menu.menu_your_post;
        }
        ((AppCompatActivity) context).getMenuInflater().inflate(menuId, menu);
    }

    @Override
    public void onMenuClick(MenuItem item) {
        if (menuId == R.menu.menu_my_post) {
            // 내 글이면

        } else {
            // 남 글이면

        }
    }

    private void initToolbar() {
        ((AppCompatActivity) context).setSupportActionBar(toolbar);
        ((AppCompatActivity) context).getSupportActionBar().setTitle("");
        ((AppCompatActivity) context).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });
    }

    private void initAdapter() {
        if (UserRepository.getInstance().getUser() != null) {
            // 로그인한 상태이면
            postAdapter = new PostAdapter(context, cover.getAuthor().getEmail().equals(UserRepository.getInstance().getUser().getEmail()));
        } else {
            // 로그인하지 않은 상태이면면
            postAdapter = new PostAdapter(context, false);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(postAdapter);
    }


    private void setCoverData() {
        if (cover != null) {
            // Cover 에서 작성한 경우
            textTitle.setText(cover.getTitle());
            textDate.setText(DateUtil.getConvertDate(cover.getStartDate(), cover.getEndDate()));
            textWriter.setText(cover.getAuthor().getUsername());

            Glide.with(context)
                    .load(cover.getCoverPath())
                    .centerCrop()
                    .into(imgCover);
            imgCover.setColorFilter(ContextCompat.getColor(context, R.color.colorPostTint), PorterDuff.Mode.SRC_OVER);

            Glide.with(context)
                    .load(cover.getAuthor().getImg_profile())
                    .into(imgProfile);

            if (UserRepository.getInstance().getUser() != null && cover.getAuthor().getEmail().equals(UserRepository.getInstance().getUser().getEmail())) {
                // 내 글인경우
                fab.setVisibility(View.VISIBLE);
            } else {
                // 남 글인경우
                fab.setVisibility(View.GONE);
            }
        }
    }

    @OnClick(R.id.fab)
    public void createText() {
        textIntent = new Intent(context, PostTextActivity.class);
        textIntent.putExtra(Const.INTENT_EXTRA_PK, cover.getPk());
        ((Activity) context).startActivityForResult(textIntent, Const.REQ_TEXT);
    }

}
