package com.hongsup.explog.view.signin.view;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hongsup.explog.R;
import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.util.VerificationUtil;
import com.hongsup.explog.view.main2.Main2Activity;
import com.hongsup.explog.view.signup.SignUpActivity;
import com.hongsup.explog.view.signin.contract.SignInContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class SignInView implements SignInContract.iView {

    private static final String TAG = "SignIn";

    @BindView(R.id.signLayout)
    ConstraintLayout signLayout;
    @BindView(R.id.imgSign)
    ImageView imgSign;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.startLayout)
    LinearLayout startLayout;
    @BindView(R.id.inputLayout)
    LinearLayout inputLayout;
    @BindView(R.id.editEmail)
    EditText editEmail;
    @BindView(R.id.editPassword)
    EditText editPassword;
    @BindView(R.id.btnFacebook)
    Button btnFacebook;

    private View view;
    private Context context;
    private SignInContract.iPresenter presenter;

    public SignInView(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.activity_sign_in, null);
        /**
         * ButterKnife Binding
         */
        ButterKnife.bind(this, view);
        setImageView();
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setPresenter(SignInContract.iPresenter presenter) {
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
        Intent intent = new Intent(context, Main2Activity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    @OnTextChanged(value = R.id.editEmail, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void detectEmailChange(CharSequence charSequence, int i, int i1, int i2){
        if(VerificationUtil.isValidEmail(charSequence.toString())){
        };
    }

    @OnTextChanged(value = R.id.editPassword, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void detectPasswordChange(CharSequence charSequence, int i, int i1, int i2){
        if(VerificationUtil.isValidPassword(charSequence.toString())){
        };
    }

    @OnClick(R.id.btnFacebook)
    public void goToMain(){
        Intent intent = new Intent(context, Main2Activity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    @OnClick(R.id.txtSignUp)
    public void goSignUp() {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.btnEmail)
    public void inputEmail() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(signLayout);
        }
        startLayout.setVisibility(View.GONE);
        inputLayout.setVisibility(View.VISIBLE);
        changeEditTextBias(true);
    }

    @OnClick(R.id.btnStart)
    public void singIn() {
        if(TextUtils.isEmpty(editEmail.getText().toString())){
            editEmail.setError("Email 을 입력하세요.");
            return;
        }

        if(TextUtils.isEmpty(editPassword.getText().toString())){
            editPassword.setError("Password 를 입력하세요.");
            return;
        }

        if(VerificationUtil.isValidEmail(editEmail.getText().toString())){
            editEmail.setError("Email 형식이 맞지 않습니다.");
            return;
        }

        if(VerificationUtil.isValidPassword(editPassword.getText().toString())){
            editPassword.setError("Password 형식이 맞지 않습니다.");
            return;
        }

        SignIn user = new SignIn();
        user.setEmail(editEmail.getText().toString());
        user.setPassword(editPassword.getText().toString());
        presenter.getSignIn(user);
    }

    /**
     * SingIn ImageView Setting
     */
    private void setImageView() {
        Glide.with(context)
                .load(R.drawable.sign)
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imgSign);
    }

    /**
     * EditText Layout 애니메이션 효과 주기
     *
     * @param flag : true 이면 위로, false 이면 아래로
     */
    private void changeEditTextBias(boolean flag) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(signLayout);
        }
        if (flag) {
            ConstraintSet set = new ConstraintSet();
            set.clone(signLayout);
            set.setVerticalBias(R.id.linearLayout, 0.52F);
            set.applyTo(signLayout);
        } else {
            ConstraintSet set = new ConstraintSet();
            set.clone(signLayout);
            set.setVerticalBias(R.id.linearLayout, 0.8F);
            set.applyTo(signLayout);
        }
    }
}
