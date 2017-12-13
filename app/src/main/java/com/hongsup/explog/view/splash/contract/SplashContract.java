package com.hongsup.explog.view.splash.contract;

import android.view.View;

import com.hongsup.explog.data.sign.SignIn;

/**
 * Created by Android Hong on 2017-11-30.
 */

public interface SplashContract {

    interface iView{
        View getView();
        void setPresenter(iPresenter presenter);

        void showProgress();
        void hideProgress();

        void showError(String text);

        void goMain();
    }
    interface iPresenter{
        void attachView(iView view);
        void getSignIn(SignIn signIn);
    }
}
