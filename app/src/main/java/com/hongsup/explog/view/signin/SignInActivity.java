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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        SignInContract.iView signInView = new SignInView(this);
        SignInContract.iPresenter signInPresenter = new SignInPresenter(this);

        signInPresenter.attachView(signInView);
        signInView.setPresenter(signInPresenter);

        setContentView(signInView.getView());
    }

/*

    @Override
    protected void onShowKeyboard(int keyboardHeight) {
        // do things when keyboard is shown
        ConstraintSet set = new ConstraintSet();
        set.clone(signLayout);
        set.setVerticalBias(R.id.linearLayout, 0.52F);
        set.applyTo(signLayout);

        Log.e(TAG, "onShowKeyboard: 키보드 위로" );
    }

    @Override
    protected void onHideKeyboard() {
        // do things when keyboard is hidden
        ConstraintSet set = new ConstraintSet();
        set.clone(signLayout);
        set.setVerticalBias(R.id.linearLayout, 0.8F);
        set.applyTo(signLayout);
        Log.e(TAG, "onShowKeyboard: 키보드 아래로" );
    }

*/

}
