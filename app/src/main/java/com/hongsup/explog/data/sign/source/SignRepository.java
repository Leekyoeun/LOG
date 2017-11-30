package com.hongsup.explog.data.sign.source;

import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.data.sign.SignUp;

import io.reactivex.Observable;
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
    public Observable<Response<SignUp>> singUp(SignUp signUp) {
        return signRemoteDataSource.singUp(signUp);
    }

    @Override
    public Observable<Response<SignIn>> signIn(SignIn signIn) {
        return signRemoteDataSource.signIn(signIn);
    }
}
