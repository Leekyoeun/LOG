package com.hongsup.explog.service;

import com.hongsup.explog.data.domain.SignIn;
import com.hongsup.explog.data.domain.SignUp;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Android Hong on 2017-11-29.
 */

public interface SignAPI {

    @POST("/member/signup/")
    Observable<Response<SignUp>> singUp(@Body SignUp signUp);

//    @POST("/member/signup/")
//    Call<SignUp> singUp(@Body User user);

    @POST("/member/login/")
    Observable<Response<SignIn>> signIn(@Body SignIn signIn);

//    @POST("/login/")
//    Call<SingIn> singIn(@Body User user);


}
