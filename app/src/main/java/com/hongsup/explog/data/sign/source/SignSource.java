package com.hongsup.explog.data.sign.source;

import android.net.Uri;

import com.hongsup.explog.data.photo.Photo;
import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.data.sign.SignUp;
import com.hongsup.explog.data.sign.SignUpResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;

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
    Observable<Response<SignUpResponse>> singUp(SignUp signUp);

    /**
     * SignIn 메소드
     *
     * @param signIn
     * @return
     */
    Observable<Response<SignIn>> signIn(SignIn signIn);

}
