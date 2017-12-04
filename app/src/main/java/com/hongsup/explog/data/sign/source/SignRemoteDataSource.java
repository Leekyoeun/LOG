package com.hongsup.explog.data.sign.source;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.data.sign.SignUp;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.api.SignAPI;

import io.reactivex.Observable;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class SignRemoteDataSource implements SignSource{

    private static SignRemoteDataSource instance;

    private SignAPI signUpAPI;
    private SignAPI signInAPI;

    private SignRemoteDataSource(){
        // Service 생성
        signUpAPI = ServiceGenerator.create(SignAPI.class, createGson(true));
        signInAPI = ServiceGenerator.create(SignAPI.class, createGson(false));
    }

    public static SignRemoteDataSource getInstance(){
        if(instance == null)
            instance = new SignRemoteDataSource();
        return instance;
    }


    @Override
    public Observable<Response<SignUp>> singUp(SignUp signUp) {
        return signUpAPI.signUp(signUp);
    }

    @Override
    public Observable<Response<SignIn>> signIn(SignIn signIn) {
        return signInAPI.signIn(signIn);
    }

    private Gson createGson(boolean nullCheck){
        GsonBuilder gsonBuilder;
        if(nullCheck){
            gsonBuilder = new GsonBuilder()
                        /*
                         excludeFieldsWithoutExposeAnnotation() : Annotation 속성을 설정한 것을 적용하는 메소드
                         */
                    .excludeFieldsWithoutExposeAnnotation()
                        /*
                         serializeNulls() : 속성이 null 일 경우 "속성명" : null 로 보내는 메소드
                         */
                    .serializeNulls();
        }else{
            gsonBuilder = new GsonBuilder()
                        /*
                         excludeFieldsWithoutExposeAnnotation() : Annotation 속성을 설정한 것을 적용하는 메소드
                         */
                    .excludeFieldsWithoutExposeAnnotation();
        }
        Gson gson = gsonBuilder.create();
        Log.e(TAG, "createGson: " + gson.toString() );
        return gson;
    }
}
