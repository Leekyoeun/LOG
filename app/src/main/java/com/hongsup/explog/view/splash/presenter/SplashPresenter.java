package com.hongsup.explog.view.splash.presenter;

import android.content.Context;
import android.util.Log;

import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.data.sign.source.SignRepository;
import com.hongsup.explog.util.PreferenceUtil;
import com.hongsup.explog.view.signin.contract.SignInContract;
import com.hongsup.explog.view.splash.contract.SplashContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class SplashPresenter implements SplashContract.iPresenter {

    private SplashContract.iView view;
    private SignRepository repository;
    Context context;

    public SplashPresenter(Context context) {
        repository = SignRepository.getInstance();
        this.context = context;
    }



    @Override
    public void attachView(SplashContract.iView view) {
        this.view = view;
    }

    @Override
    public void getSignIn(SignIn signIn) {
        view.showProgress();

        /**
         * Observable Pattern 으로 한 경우
         */
        Observable<Response<SignIn>> observable = repository.signIn(signIn);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    // next
                    Log.e("SignInActivity", data.code() + ", " + data.message());
                    if(data.isSuccessful()){
                        if (data.code() == 200) {
                            view.hideProgress();
                            /**
                             * 값을 넘겨주거나, SharedPreference 에 Token, Email, img_path, NickName 을 저장한다.
                             */
                            PreferenceUtil.setValue(context, "Email", signIn.getEmail());
                            PreferenceUtil.setValue(context, "password", signIn.getPassword());
                            PreferenceUtil.setValue(context, "AutoSignIn", "true");
                            // Token 값, profile_img url값 받아 SharedPreference에 넣음 12/5
                            PreferenceUtil.setValue(context, "token", data.body().getToken());
                            if("".equals(data.body().getProfile_img())) {
                                PreferenceUtil.setValue(context, "profile_img", data.body().getProfile_img());
                                Log.d("SignInAcitivy!!!!", data.body().getProfile_img());
                            }


                            view.goMain();
                        }
                    }else{
                        Log.e("SignInActivity", "getSignIn: Error");
                    }

                }, throwable -> {
                    Log.e("SignInActivity", throwable.getMessage());
                    // error;
                    view.hideProgress();
                    view.showError();
                });
    }
}
