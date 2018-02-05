package com.hongsup.explog.view.post.contract;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.post.Reply;
import com.hongsup.explog.data.user.User;
import com.hongsup.explog.view.post.adapter.contract.PostAdapterContract;

import java.util.ArrayList;

/**
 * Created by Android Hong on 2017-12-14.
 */

public interface PostContract {

    interface iView {
        View getView();

        void setPresenter(iPresenter presenter);

        void showProgress();

        void hideProgress();

        void showError();

        void setMenu(Menu menu);

        void onMenuClick(MenuItem item);

        void recyclerDown(int position);
    }

    interface iPresenter {

        void attachView(iView view);

        void loadPostContent(PostCover cover);

        void setPostAdapterModel(PostAdapterContract.iModel model);

        void setPostAdapterView(PostAdapterContract.iView view);

        void uploadPostText(String text, String date);

        void uploadPostPath(double lat, double lng);

        void uploadPostPhoto(String photoPath);

        void loadFollowing(ArrayList<User> list);

        void setOnPostDelete();
    }
}
