package com.hongsup.explog.data.sign.source;

import android.net.Uri;

import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.data.sign.SignUp;
import com.hongsup.explog.data.sign.SignUpResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class SignRepository implements SignSource {

    private static SignRepository instance;

    private SignRemoteDataSource signRemoteDataSource;

    private SignRepository(){
        signRemoteDataSource = SignRemoteDataSource.getInstance();
    }

    public static SignRepository getInstance(){
        if(instance == null)
            instance = new SignRepository();
        return instance;
    }

    @Override
    public Observable<Response<SignUpResponse>> singUp(SignUp signUp) {
        return signRemoteDataSource.singUp(signUp);
    }

    @Override
    public Observable<Response<SignIn>> signIn(SignIn signIn) {
        return signRemoteDataSource.signIn(signIn);
    }
}
