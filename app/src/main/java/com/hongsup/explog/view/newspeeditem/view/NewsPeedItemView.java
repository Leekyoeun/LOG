package com.hongsup.explog.view.newspeeditem.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.view.custom.PostItemDivider;
import com.hongsup.explog.view.newspeeditem.adapter.NewsPeedItemAdapter;
import com.hongsup.explog.view.newspeeditem.contract.NewsPeedItemContract;
import com.hongsup.explog.view.post.PostActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class NewsPeedItemView extends FrameLayout implements NewsPeedItemContract.iView {

    private static final String TAG = "MainSubView";

    private int index;
    private Context context;
    private NewsPeedItemContract.iPresenter newsPeedItemPresenter;
    private NewsPeedItemAdapter newsPeedItemAdapter;

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;


    public NewsPeedItemView(@NonNull Context context, int index) {
        super(context);
        this.context = context;
        this.index = index;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_newspeed_content, null);
        ButterKnife.bind(this, view);
        // 로직 처리
        initAdapter();
        addView(view);
    }

    private void initAdapter() {
        newsPeedItemAdapter = new NewsPeedItemAdapter();
        recyclerView.setAdapter(newsPeedItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        /*
        RecyclerView 사이에 여백 주는 Code
         */
        recyclerView.addItemDecoration(new PostItemDivider(48));

        // RecyclerView Animation
        /*
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(context, resId);
        recyclerView.setLayoutAnimation(animation);
        */
    }

    @Override
    public void setPresenter(NewsPeedItemContract.iPresenter presenter) {
        this.newsPeedItemPresenter = presenter;
        this.newsPeedItemPresenter.setNewsPeedItemAdapterModel(newsPeedItemAdapter);
        this.newsPeedItemPresenter.setNewsPeedItemAdapterView(newsPeedItemAdapter);
        this.newsPeedItemPresenter.loadItem(index);
    }

    @Override
    public void goToPost(PostCover postCover) {
        Intent intent = new Intent(context, PostActivity.class);

        /**
         * 값을 넘겨줘야 한다.
         */
        intent.putExtra("COVER",postCover);
        context.startActivity(intent);
    }

    @Override
    public void showError(String text) {

    }

}
