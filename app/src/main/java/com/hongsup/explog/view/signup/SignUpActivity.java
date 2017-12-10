package com.hongsup.explog.view.signup;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.hongsup.explog.data.photo.Photo;
import com.hongsup.explog.view.signup.contract.SignUpContract;
import com.hongsup.explog.view.signup.presenter.SignUpPresenter;
import com.hongsup.explog.view.signup.view.SignUpView;

import java.util.ArrayList;

import static com.hongsup.explog.data.Const.REQ_GALLERY;

public class SignUpActivity extends AppCompatActivity {
    SignUpContract.iView signUpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        signUpView = new SignUpView(this);
        SignUpContract.iPresenter signUpPresenter = new SignUpPresenter();

        signUpPresenter.attachView(signUpView);
        signUpView.setPresenter(signUpPresenter);

        setContentView(signUpView.getView());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case REQ_GALLERY :
                if(resultCode == RESULT_OK){
                    if(data != null){
                        ArrayList<Photo> photoList = (ArrayList<Photo>) data.getSerializableExtra("PHOTO");

                        signUpView.setImageView(photoList.get(0));

//                        data.getSerializableExtra()
//                        signUpView.setImageView(photo);
                    }
                }
        }
    }
}
