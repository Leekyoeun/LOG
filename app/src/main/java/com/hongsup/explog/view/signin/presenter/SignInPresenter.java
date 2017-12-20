package com.hongsup.explog.view.signin.presenter;

import android.content.Context;
import android.util.Log;

import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.data.sign.source.SignRepository;
import com.hongsup.explog.data.user.User;
import com.hongsup.explog.data.user.source.UserRepository;
import com.hongsup.explog.util.PreferenceUtil;
import com.hongsup.explog.view.signin.contract.SignInContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class SignInPresenter implements SignInContract.iPresenter {

    private SignInContract.iView view;
    private SignRepository repository;
    private Context context;

    public SignInPresenter(Context context) {
        repository = SignRepository.getInstance();
        this.context = context;
    }

    @Override
    public void attachView(SignInContract.iView view) {
        this.view = view;
    }

    @Override
    public void getSignIn(SignIn signIn) {
        view.showProgress();

        /**
         * Observable Pattern 으로 한 경우
         */
        Observable<Response<User>> observable = repository.signIn(signIn);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    // next
                    Log.e(TAG, "getSignIn: " + data.code() );
                    if (data.isSuccessful()) {
                        if (data.code() == 200) {
                            /**
                             * 로그인이 성공했을 경우
                             */

                            view.hideProgress();

                            /**
                             * 1. 자동 로그인에 필요한 SharedPreference 에 저장한다.
                             */
                            PreferenceUtil.setValue(context, "Email", signIn.getEmail());
                            PreferenceUtil.setValue(context, "password", signIn.getPassword());
                            PreferenceUtil.setValue(context, "token", data.body().getToken());

                            /**
                             * 2. 회원 정보를 저장한다.
                             */
                            UserRepository.getInstance().setUser(data.body());
                            Log.e(TAG, "getSignIn: " + data.body().toString());
                            view.goMain();
                        } else {

                            /**
                             * 로그인이 실패하였을 경우
                             */
                            view.hideProgress();
                            view.showError("로그인 실패 1");

                            PreferenceUtil.removeAllValue(context);
                            UserRepository.getInstance().clearUser();
                        }
                    } else {
                        view.hideProgress();
                        view.showError("로그인 실패 2");

                        PreferenceUtil.removeAllValue(context);
                        UserRepository.getInstance().clearUser();
                    }
                }, throwable -> {
                    view.hideProgress();
                    view.showError("로그인 실패 2 " + throwable.getMessage());

                    PreferenceUtil.removeAllValue(context);
                    UserRepository.getInstance().clearUser();
                });
    }
}
