package com.hongsup.explog.view.signin;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.hongsup.explog.view.signin.contract.SignInContract;
import com.hongsup.explog.view.signin.presenter.SignInPresenter;
import com.hongsup.explog.view.signin.view.SignInView;


public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().setStatusBarColor(getResources()
//                    .getColor(R.color.colorWhite));
        }


        SignInContract.iView signInView = new SignInView(this);
        SignInContract.iPresenter signInPresenter = new SignInPresenter();

        signInPresenter.attachView(signInView);
        signInView.setPresenter(signInPresenter);

        setContentView(signInView.getView());
    }

}
