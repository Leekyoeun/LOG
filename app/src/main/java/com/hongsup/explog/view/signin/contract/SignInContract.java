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
        void showError(String text);

        // Main 으로 가는 메소드
        void goMain();

        // 현재 inputEmail Mode 인지 확인하는 메소드
        boolean isInputEmail();

        // Layout 초기화 메소드
        void layoutReset();

    }
    interface iPresenter{
        void attachView(iView view);
        void getSignIn(SignIn signIn);
    }
}
