package com.hongsup.explog.data.sign.source;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.data.sign.SignUp;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.SignAPI;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class SignRemoteDataSource implements SignSource {

    private static SignRemoteDataSource instance;

    private SignAPI signUpAPI;
    private SignAPI signInAPI;

    private SignRemoteDataSource() {
        // Service 생성
        signUpAPI = ServiceGenerator.create(SignAPI.class, createGson(true));
        signInAPI = ServiceGenerator.create(SignAPI.class, createGson(false));
    }

    public static SignRemoteDataSource getInstance() {
        if (instance == null)
            instance = new SignRemoteDataSource();
        return instance;
    }

    @Override
    public Observable<Response<SignUp>> singUp(SignUp signUp) {

        signUpAPI = ServiceGenerator.create(SignAPI.class);

        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("password1", toRequestBody(signUp.getPassword1()));
        requestBodyMap.put("password2", toRequestBody(signUp.getPassword2()));
        requestBodyMap.put("email", toRequestBody(signUp.getEmail()));
        requestBodyMap.put("username", toRequestBody(signUp.getUsername()));


        Log.e(TAG, "singUp: " + signUp.getImg_profile() );

        if(signUp.getImg_profile() != null){
            File file = new File(signUp.getImg_profile());
            // create RequestBody instance from file
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            requestBodyMap.put("img_profile\"; filename=\""+file.getName() +"\"", requestFile);
        }else{
            RequestBody requestFile = RequestBody.create(MediaType.parse(""),"");
            requestBodyMap.put("img_profile\"; filename=\"\"", requestFile);
        }

        return signUpAPI.signUp(requestBodyMap);
    }

    @Override
    public Observable<Response<SignIn>> signIn(SignIn signIn) {
        return signInAPI.signIn(signIn);
    }

    private Gson createGson(boolean nullCheck) {
        GsonBuilder gsonBuilder;
        if (nullCheck) {
            gsonBuilder = new GsonBuilder()
                        /*
                         excludeFieldsWithoutExposeAnnotation() : Annotation 속성을 설정한 것을 적용하는 메소드
                         */
                    .excludeFieldsWithoutExposeAnnotation()
                        /*
                         serializeNulls() : 속성이 null 일 경우 "속성명" : null 로 보내는 메소드
                         */
                    .serializeNulls();
        } else {
            gsonBuilder = new GsonBuilder()
                        /*
                         excludeFieldsWithoutExposeAnnotation() : Annotation 속성을 설정한 것을 적용하는 메소드
                         */
                    .excludeFieldsWithoutExposeAnnotation();
        }
        Gson gson = gsonBuilder.create();
        Log.e(TAG, "create Gson: " + gson.toString());
        return gson;
    }


    public static RequestBody toRequestBody(String json) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), json);
        return body;
    }
}
