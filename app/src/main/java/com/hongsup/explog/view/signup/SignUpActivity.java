package com.hongsup.explog.view.signup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hongsup.explog.view.signup.contract.SignUpContract;
import com.hongsup.explog.view.signup.presenter.SignUpPresenter;
import com.hongsup.explog.view.signup.view.SignUpView;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SignUpContract.iView signUpView = new SignUpView(this);
        SignUpContract.iPresenter signUpPresenter = new SignUpPresenter();

        signUpPresenter.attachView(signUpView);
        signUpView.setPresenter(signUpPresenter);

        setContentView(signUpView.getView());
    }
}
