package com.hongsup.explog.view.splash.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.text.TextUtils;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hongsup.explog.R;
import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.util.VerificationUtil;
import com.hongsup.explog.view.main.MainActivity;
import com.hongsup.explog.view.signin.contract.SignInContract;
import com.hongsup.explog.view.signup.SignUpActivity;
import com.hongsup.explog.view.splash.contract.SplashContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

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
