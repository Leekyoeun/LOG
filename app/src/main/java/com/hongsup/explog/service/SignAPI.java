package com.hongsup.explog.service;

import com.hongsup.explog.data.domain.ResponseBody;
import com.hongsup.explog.data.domain.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Android Hong on 2017-11-29.
 */

public interface SignAPI {

    @POST("/member/signup/")
    Call<ResponseBody> insertUser(
            @Body User user
    );

}
