package com.hongsup.explog.view.splash;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.hongsup.explog.R;
import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.util.PreferenceUtil;
import com.hongsup.explog.view.signin.SignInActivity;
import com.hongsup.explog.view.signin.presenter.SignInPresenter;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Theme 의 StatusBar 투명하게 처리하기 위한 방법
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 2초 뒤 실행
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 자동 로그인 처리
                /*if("true".equals(PreferenceUtil.getString(SplashActivity.this, "AutoSignIn"))){
                    SignIn user = new SignIn();
                    user.setEmail(PreferenceUtil.getString(SplashActivity.this, "Email"));
                    user.setPassword(PreferenceUtil.getString(SplashActivity.this, "password"));
                    SignInPresenter presenter = new SignInPresenter(SplashActivity.this);
                    presenter.getSignIn(user);
//                    Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
//                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                }*/
                Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();

            }
        }, 2000);
    }
}
