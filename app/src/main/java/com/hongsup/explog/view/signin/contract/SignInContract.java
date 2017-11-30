package com.hongsup.explog.view.signin.contract;

import android.view.View;

import com.hongsup.explog.data.sign.SignIn;

/**
 * Created by Android Hong on 2017-11-30.
 */

public interface SignInContract {

    interface iView{
        View getView();
        void setPresenter(iPresenter presenter);

        void showProgress();
        void hideProgress();

        void showError();

        void goMain();
    }
    interface iPresenter{
        void attachView(iView view);
        void getSignIn(SignIn signIn);
    }
}
