package com.hongsup.explog.view.sign;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hongsup.explog.R;
import com.hongsup.explog.data.domain.SignIn;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.SignAPI;
import com.hongsup.explog.view.main.MainActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "SignInActivity";
    private ConstraintLayout signLayout;
    private ImageView imgSign;
    private LinearLayout linearLayout, startLayout, inputLayout;
    private Button btnEmail, btnStart;
    private TextView txtSignUp;

    private EditText editEmail, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_sign_in);
        initView();
    }

    private void initView() {
        imgSign = findViewById(R.id.imgSign);
        btnEmail = findViewById(R.id.btnEmail);
        startLayout = findViewById(R.id.startLayout);
        inputLayout = findViewById(R.id.inputLayout);
        signLayout = findViewById(R.id.signLayout);
        linearLayout = findViewById(R.id.linearLayout);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnStart = findViewById(R.id.btnStart);
        txtSignUp = findViewById(R.id.txtSignUp);

        Glide.with(this)
                .load(R.drawable.sign)
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imgSign);

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(signLayout);
                }
                startLayout.setVisibility(View.GONE);
                inputLayout.setVisibility(View.VISIBLE);
                changeEditTextBias(true);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn user = new SignIn();
                user.setEmail(editEmail.getText().toString());
                user.setPassword(editPassword.getText().toString());

                /**
                 * Observable Pattern 으로 한 경우
                 */

                Gson gson = new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .create();
                SignAPI signAPI = ServiceGenerator.create(SignAPI.class, gson);

                Observable<Response<SignIn>> observable = signAPI.signIn(user);
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(data -> {
                            // next
                            //Log.e(TAG, "onClick: " + data.code() );
                            //Log.e("SignInActivity", data.body().toString());
                            Log.e("SignInActivity", data.code() + ", " + data.message());

                            if (data.code() == 200) {
                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                                Log.e(TAG, data.body().toString());
                            }
                        }, throwable -> {
                            // error;
                            Log.e("SignInActivity", "throwable 발생");
                            Log.e("SignInActivity", throwable.getMessage());
                        }, () -> {
                            // Complete;
                            Log.e("SignInActivity", "Complete 발생");

                        });

            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

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
