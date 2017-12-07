package com.hongsup.explog.view.signin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hongsup.explog.view.signin.contract.SignInContract;
import com.hongsup.explog.view.signin.presenter.SignInPresenter;
import com.hongsup.explog.view.signin.view.SignInView;


public class SignInActivity extends AppCompatActivity {

    SignInContract.iView signInView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }*/

        signInView = new SignInView(this);
        SignInContract.iPresenter signInPresenter = new SignInPresenter(this);

        signInPresenter.attachView(signInView);
        signInView.setPresenter(signInPresenter);

        setContentView(signInView.getView());
    }

    @Override
    public void onBackPressed() {
        if(signInView.isInputEmail()){
            signInView.layoutReset();
        }else{
            super.onBackPressed();
        }
    }
}
