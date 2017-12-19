package com.hongsup.explog.service.api;

import com.hongsup.explog.view.setting.editprofile.insuptest.UserEditProfile;
import com.hongsup.explog.view.setting.editprofile.insuptest.UserInformation;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.Part;

/**
 * Created by 정인섭 on 2017-12-14.
 */

public interface EditProfileAPI {
    @GET("/member/userprofile/")
    Observable<Response<UserInformation>> getUserInfo();

    @Multipart
    @PATCH("/member/userprofile/update/")
    Observable<Response<UserEditProfile>> userEditProfile(@Part MultipartBody.Part filePart, @Part("username") RequestBody username);
}
