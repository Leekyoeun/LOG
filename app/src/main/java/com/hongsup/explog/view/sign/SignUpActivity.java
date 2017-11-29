package com.hongsup.explog.view.sign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hongsup.explog.R;
import com.hongsup.explog.data.domain.ResponseBody;
import com.hongsup.explog.data.domain.User;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.SignAPI;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setListener();
        setBackground();
    }

    private void setBackground(){
        Glide.with(this)
                .load(R.drawable.signup)
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imgBackground);
    }

    private void setListener(){
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ContentResolver를 이용하여 사진 불러오기 해야 함
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.email = etEmail.getText().toString();
                user.password1 = etPassword.getText().toString();
                user.password2 = etPasswordConfirm.getText().toString();
                user.username = etNickName.getText().toString();
                user.img_profile = null;

                SignAPI signUpAPI = ServiceGenerator.create(SignAPI.class);

                Call<ResponseBody> call = signUpAPI.insertUser(user);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        Log.d("====response status", response.message());
//                        Log.d("====response", response.code()+"");
//                        Log.d("====req", "onResponse: " + response.body().email);
//                        Log.d("====성공하였습니다", "성공");
                        //Log.d("gggg", response.body().username + " ");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("====실패하였습니다", "실패");
                    }
                });
            }
        });
    }

}
