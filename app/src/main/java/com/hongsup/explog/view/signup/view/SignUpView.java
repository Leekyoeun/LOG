package com.hongsup.explog.view.signup.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hongsup.explog.R;
import com.hongsup.explog.data.photo.Photo;
import com.hongsup.explog.data.sign.SignUp;
import com.hongsup.explog.util.VerificationUtil;
import com.hongsup.explog.view.gallery.GalleryActivity;
import com.hongsup.explog.view.signin.SignInActivity;
import com.hongsup.explog.view.signup.contract.SignUpContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class SignUpView implements SignUpContract.iView{

    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etPasswordConfirm)
    EditText etPasswordConfirm;
    @BindView(R.id.etNickName)
    EditText etNickName;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    @BindView(R.id.imgBackground)
    ImageView imgBackground;

    private Context context;
    private View view;
    private SignUpContract.iPresenter presenter;
    private static final int REQ_GALLERY = 999;
    Photo photo;

    public SignUpView(Context context){
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.activity_sign_up, null);
        /**
         * ButterKnife Binding
         */
        ButterKnife.bind(this, view);
        setProfileBackground();
        setBackground();
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setPresenter(SignUpContract.iPresenter presenter) {
        this.presenter = presenter;
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
    public void goSignIn() {
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    @Override
    public void setImageView(Photo photo) {
        this.photo = photo;
        Glide.with(context)
        .load(photo.getImagePath())
        .centerCrop()
        .into(imgProfile);
    }

    private void setProfileBackground(){
        Glide.with(context)
                .load(android.R.drawable.ic_input_add)
                .fitCenter()
                .centerCrop()
                .into(imgProfile);
    }


    private void setBackground() {
        Glide.with(context)
                .load(R.drawable.signup)
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imgBackground);
    }

//    @OnTextChanged(value = R.id.etEmail, callback = OnTextChanged.Callback.TEXT_CHANGED)
//    public void detectEmailChange(CharSequence charSequence, int i, int i1, int i2){
//        if(VerificationUtil.isValidEmail(charSequence.toString())){
//        };
//    }
//
//    @OnTextChanged(value = R.id.etPassword, callback = OnTextChanged.Callback.TEXT_CHANGED)
//    public void detectPasswordChange(CharSequence charSequence, int i, int i1, int i2){
//        if(VerificationUtil.isValidPassword(charSequence.toString())){
//        };
//    }
//
//    @OnTextChanged(value = R.id.etPasswordConfirm, callback = OnTextChanged.Callback.TEXT_CHANGED)
//    public void detectSecondPasswordChange(CharSequence charSequence, int i, int i1, int i2){
//        if(VerificationUtil.isValidPassword(charSequence.toString())){
//        };
//    }
//
//    @OnTextChanged(value = R.id.etNickName, callback = OnTextChanged.Callback.TEXT_CHANGED)
//    public void detectNameChange(CharSequence charSequence, int i, int i1, int i2){
//        if(VerificationUtil.isValidName(charSequence.toString())){
//        };
//    }

    @OnClick(R.id.imgProfile)
    public void getImage(){
        //ContentResolver 를 이용하여 사진 불러오기 해야 함
        Intent intent = new Intent(context, GalleryActivity.class);
        ((Activity) context).startActivityForResult(intent, REQ_GALLERY);
    }

    @OnClick(R.id.btnSignUp)
    public void setSignUp(){

        if(TextUtils.isEmpty(etEmail.getText().toString())){
            etEmail.setError("Email 을 입력하세요.");
            return;
        }

        if(TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.setError("Password 를 입력하세요.");
            return;
        }

        if(TextUtils.isEmpty(etPasswordConfirm.getText().toString())){
            etPasswordConfirm.setError("Password 를 입력하세요.");
            return;
        }

        if(TextUtils.isEmpty(etNickName.getText().toString())){
            etNickName.setError("Nickname을 입력하세요.");
            return;
        }

        if(VerificationUtil.isValidEmail(etEmail.getText().toString())){
            etEmail.setError("Email 형식이 맞지 않습니다.");
            return;
        }

        if(VerificationUtil.isValidPassword(etPassword.getText().toString())){
            etPassword.setError("Password 형식이 맞지 않습니다.");
            return;
        }

        if(VerificationUtil.isValidPassword(etPasswordConfirm.getText().toString())){
            etPasswordConfirm.setError("Password 형식이 맞지 않습니다.");
            return;
        }

        if(VerificationUtil.isValidName(etNickName.getText().toString())){
            etNickName.setError("NickName 형식이 맞지 않습니다.");
            return;
        }


        /**
         * 백엔드쪽 데이터 파라미터 수정에 의해 password1, password2가 password 하나로 합쳐짐 12/5
         * password 유효성 검사 추가 12/5
         */
        if(etPassword.getText().toString().equals(etPasswordConfirm.getText().toString())) {
            SignUp signUp = new SignUp();
            signUp.setEmail(etEmail.getText().toString());
            signUp.setPassword(etPassword.getText().toString());
            signUp.setUsername(etNickName.getText().toString());
            if (photo != null) {
                signUp.setImg_profile(photo.getImagePath());
            }
            Log.e("setSignUp", "setSignUp: " + signUp.toString());

            presenter.setSignUp(signUp);
        }else{
            //Dialog를 띄워도 될 것 같음
            if(VerificationUtil.isValidPassword(etPasswordConfirm.getText().toString())){
                etPasswordConfirm.setError("Password가 일치하지 않습니다");
            }
        }
    }

}
