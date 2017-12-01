package com.hongsup.explog.view.signup.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.hongsup.explog.view.gallery.GalleryActivity;
import com.hongsup.explog.view.signin.SignInActivity;
import com.hongsup.explog.view.signup.contract.SignUpContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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


    private void setBackground() {
        Glide.with(context)
                .load(R.drawable.signup)
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imgBackground);
    }

    @OnClick(R.id.imgProfile)
    public void getImage(){
        //ContentResolver 를 이용하여 사진 불러오기 해야 함
        Intent intent = new Intent(context, GalleryActivity.class);
        ((Activity) context).startActivityForResult(intent, REQ_GALLERY);
    }

    @OnClick(R.id.btnSignUp)
    public void setSignUp(){
        SignUp signUp = new SignUp();
        signUp.setEmail(etEmail.getText().toString());
        signUp.setPassword1(etPassword.getText().toString());
        signUp.setPassword2(etPasswordConfirm.getText().toString());
        signUp.setUsername(etNickName.getText().toString());
        if(photo!=null) {
            signUp.setImg_profile(photo.getImagePath());
        }
        Log.e("setSignUp", "setSignUp: " + signUp.toString() );

        presenter.setSignUp(signUp);
    }

}
