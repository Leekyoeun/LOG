package com.hongsup.explog.view.post.contract;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hongsup.explog.data.post.UploadPostText;
import com.hongsup.explog.view.post.adapter.contract.PostAdapterContract;

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
    }

    interface iPresenter {
        void attachView(iView view);

        void loadPostContent(int postPk);

        void setPostAdapterModel(PostAdapterContract.iModel model);

        void setPostAdapterView(PostAdapterContract.iView view);

        void uploadPostText(UploadPostText postText);

        void uploadPostPath(double lat, double lng);

        void uploadPostPhoto(String photoPath);
    }
}