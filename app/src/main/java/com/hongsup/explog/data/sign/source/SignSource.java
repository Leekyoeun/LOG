package com.hongsup.explog.data.sign.source;

import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.data.sign.SignUp;
import com.hongsup.explog.data.user.User;

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
    Observable<Response<User>> singUp(SignUp signUp);

    /**
     * SignIn 메소드
     *
     * @param signIn
     * @return
     */
    Observable<Response<User>> signIn(SignIn signIn);



}
