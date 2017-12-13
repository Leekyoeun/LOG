package com.hongsup.explog.view.signin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hongsup.explog.view.signin.contract.SignInContract;
import com.hongsup.explog.view.signin.presenter.SignInPresenter;
import com.hongsup.explog.view.signin.view.SignInView;


public class SignInActivity extends AppCompatActivity {

    private SignInContract.iView signInView;
    private SignInContract.iPresenter signInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signInView = new SignInView(this);
        signInPresenter = new SignInPresenter(this);

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
