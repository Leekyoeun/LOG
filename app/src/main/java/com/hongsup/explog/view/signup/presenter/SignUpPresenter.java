package com.hongsup.explog.view.signup.presenter;

import android.util.Log;

import com.hongsup.explog.data.sign.SignUp;
import com.hongsup.explog.data.sign.source.SignRepository;
import com.hongsup.explog.view.signup.contract.SignUpContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class SignUpPresenter implements SignUpContract.iPresenter {

    private SignRepository repository;
    private SignUpContract.iView view;

    public SignUpPresenter() {
        repository = SignRepository.getInstance();
    }

    @Override
    public void attachView(SignUpContract.iView view) {
        this.view = view;
    }

    @Override
    public void setSignUp(SignUp signUp) {

        view.showProgress();
        /**
         * Observable Pattern 으로 한 경우
         */
        Observable<Response<SignUp>> observable = repository.singUp(signUp);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    Log.e("SignUpActivity", data.code() + ", " + data.message());
                    if (data.isSuccessful()) {
                        if (data.code() == 200) {
                            // next

                            /*
                            SignUp user = new SignUp();
                            user.setCode(data.code());
                            user.setPk(data.body().getPk());
                            user.setToken(data.body().getToken());
                            */
                            Log.e("SignUpActivity", "onResponse: " + data.body().toString());

                            view.hideProgress();
                            view.goSignIn();
                        }
                    } else {
                        Log.e("SignUpActivity", "setSignUp: Error");
                    }
                }, throwable -> {
                    // error;
                    Log.e("SignUpActivity", "throwable 발생");
                    Log.e("SIgnUpActivity", throwable.getMessage());

                    view.showError();
                    view.hideProgress();
                });

    }
}
