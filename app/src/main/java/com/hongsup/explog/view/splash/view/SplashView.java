package com.hongsup.explog.view.splash.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.hongsup.explog.R;
import com.hongsup.explog.view.main.MainActivity;
import com.hongsup.explog.view.splash.contract.SplashContract;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class SplashView implements SplashContract.iView {

    private static final String TAG = "SignIn";

    private View view;
    private Context context;
    private SplashContract.iPresenter presenter;

    public SplashView(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.activity_splash, null);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setPresenter(SplashContract.iPresenter presenter) {
        this.presenter = presenter;

        /**
         * 데이터를 로드하는 구간이 있으면 여기서 로드한다.
         */
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void goMain() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

}
