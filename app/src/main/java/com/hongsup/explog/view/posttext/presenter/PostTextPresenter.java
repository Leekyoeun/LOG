package com.hongsup.explog.view.posttext.presenter;

import com.hongsup.explog.view.posttext.contract.PostTextContract;

/**
 * Created by Hong on 2017-12-14.
 */

public class PostTextPresenter implements PostTextContract.iPresenter{

    private PostTextContract.iView view;

    @Override
    public void attachView(PostTextContract.iView view) {
        this.view = view;
    }
}
