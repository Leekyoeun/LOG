package com.hongsup.explog.data.sign.source;

import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.data.sign.SignUp;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Android Hong on 2017-11-30.
 */

public interface SignSource {

    /**
     * SignUp 메소드
     *
     * @param signUp
     * @return
     */
    Observable<Response<SignUp>> singUp(SignUp signUp);

    /**
     * SignIn 메소드
     *
     * @param signIn
     * @return
     */
    Observable<Response<SignIn>> signIn(SignIn signIn);

}
