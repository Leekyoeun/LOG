package com.hongsup.explog.view.newspeeditem.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.hongsup.explog.R;
import com.hongsup.explog.data.Const;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.user.User;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.api.EditProfileAPI;
import com.hongsup.explog.view.custom.PostItemDivider;
import com.hongsup.explog.view.newspeeditem.adapter.NewsPeedItemAdapter;
import com.hongsup.explog.view.newspeeditem.contract.NewsPeedItemContract;
import com.hongsup.explog.view.post.PostActivity;
import com.hongsup.explog.view.setting.editprofile.insuptest.UserInformation;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class NewsPeedItemView extends FrameLayout implements NewsPeedItemContract.iView {

    private static final String TAG = "MainSubView";

    private int index;
    private Context context;
    private NewsPeedItemContract.iPresenter newsPeedItemPresenter;
    private NewsPeedItemAdapter newsPeedItemAdapter;
    private ArrayList<User> userList;

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
        loadFollowInfo();
        Intent intent = new Intent(context, PostActivity.class);
        /**
         * 값을 넘겨줘야 한다.
         */
        intent.putExtra(Const.INTENT_EXTRA_COVER,postCover);
        intent.putExtra("userList", userList);
        context.startActivity(intent);
    }

    public void loadFollowInfo(){
        EditProfileAPI profileEditAPI = ServiceGenerator.createInterceptor(EditProfileAPI.class);
        Observable<Response<UserInformation>> getUserInfo = profileEditAPI.getUserInfo();
        getUserInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    if (data.isSuccessful()) {
                        if (data.code() == 200) {
                            Log.d("PostPresenter", "확인됨");

                            userList = data.body().getFollowing_users();


                        } else {
                            Log.d("PostPresenter", "확인안됨");
                        }
                    } else {
                        Log.d("PostPresenter", data.errorBody().string() + "data unsuccessful");
                    }
                }, throwable -> {
                    Log.e("PostPresenter", throwable.getMessage());
                });
    }

    @Override
    public void showError(String text) {

    }

}
