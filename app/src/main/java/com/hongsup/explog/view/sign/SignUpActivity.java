package com.hongsup.explog.view.sign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hongsup.explog.R;
import com.hongsup.explog.data.domain.SignUp;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.SignAPI;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setListener();
    }

    private void setListener() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp user = new SignUp();
                user.setEmail(etEmail.getText().toString());
                user.setPassword1(etPassword.getText().toString());
                user.setPassword2(etPasswordConfirm.getText().toString());
                user.setUsername(etNickName.getText().toString());
                user.setImg_profile(null);

                /*
                user.email = etEmail.getText().toString();
                user.password1 = etPassword.getText().toString();
                user.password2 = etPasswordConfirm.getText().toString();
                user.username = etNickName.getText().toString();
                user.img_profile = null;
                */

                /**
                 * Observable Pattern 으로 한 경우
                 */

                Gson gson = new GsonBuilder()
                        /*
                         excludeFieldsWithoutExposeAnnotation() : Annotation 속성을 설정한 것을 적용하는 메소드
                         */
                        .excludeFieldsWithoutExposeAnnotation()
                        /*
                         serializeNulls() : 속성이 null 일 경우 "속성명" : null 로 보내는 메소드
                         */
                        .serializeNulls()
                        .create();

                SignAPI signAPI = ServiceGenerator.create(SignAPI.class, gson);

                Observable<Response<SignUp>> observable = signAPI.singUp(user);

                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(data -> {
                            Log.e("SignUpActivity", "onClick: " + data.message());
                            Log.e("SignUpActivity", "onClick: " + data.code());
                            Log.e("SignUpActivity", "onClick: " + data.isSuccessful());

                            if (data.isSuccessful()) {
                                if (data.code() == 200) {
                                    // next
                                    SignUp signUp = new SignUp();
                                    signUp.setCode(data.code());
                                    signUp.setPk(data.body().getPk());
                                    signUp.setToken(data.body().getToken());
                                    Log.e("SignUpActivity", "onResponse: " + data.body().toString());
                                }
                            }
                        }, throwable -> {
                            // error;
                            Log.e("SignUpActivity", "throwable 발생");
                            Log.e("SIgnUpActivity", throwable.getMessage());
                        }, () -> {
                            // Complete;
                            Log.e("SignUpActivity", "Complete 발생");
                        });

                /*
                Retrofit Call<T>.enqueue 로 한 경우

                Call<SignUp> call = signAPI.singUp(user);
                call.enqueue(new Callback<SignUp>() {
                    @Override
                    public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                        Log.d("====response status", response.message());
                        Log.d("====response", response.code()+"");
                        Log.d("====req", "onResponse: " + response.body());
                        Log.d("====성공하였습니다", "성공");
                        Log.d("gggg", response.body().getUsername() + " ");
                    }

                    @Override
                    public void onFailure(Call<SignUp> call, Throwable t) {
                        Log.d("====실패하였습니다", "실패");
                    }
                });
                */

            }
        });
    }

}
