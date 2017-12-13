package com.hongsup.explog.service.api;

import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.data.user.User;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * Created by Android Hong on 2017-11-29.
 */

public interface SignAPI {

    @Multipart
    @POST("/member/signup/")
    Observable<Response<User>> signUp(@PartMap Map<String, RequestBody> signUpMap);

    @POST("/member/login/")
    Observable<Response<User>> signIn(@Body SignIn signIn);

}
