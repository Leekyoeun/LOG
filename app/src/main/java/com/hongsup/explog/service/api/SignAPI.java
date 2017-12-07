package com.hongsup.explog.service.api;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.data.sign.SignUp;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by Android Hong on 2017-11-29.
 */

public interface SignAPI {

    @POST("/member/signup/")
    Observable<Response<SignUp>> signUp(@Body SignUp signUp);

    @Multipart
    @POST("/member/signup/")
    Observable<Response<SignUp>> signUp(@PartMap Map<String, RequestBody> signUpMap);

//    @POST("/member/signup/")
//    Call<SignUp> singUp(@Body User user);

    @POST("/member/login/")
    Observable<Response<SignIn>> signIn(@Body SignIn signIn);

//    @POST("/login/")
//    Call<SingIn> singIn(@Body User user);


}
