package com.hongsup.explog.data.post.source;

import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.data.post.PostContentResult;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.post.PostResult;
import com.hongsup.explog.data.post.UploadPostText;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.api.PostAPI;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * Created by Android Hong on 2017-12-13.
 */

public class PostRemoteDataSource implements PostSource{

    private static PostRemoteDataSource instance;

    private PostAPI postAPI;
    private PostAPI postTokenAPI;

    private PostRemoteDataSource() {
        // Service 생성
        postAPI = ServiceGenerator.create(PostAPI.class);
        postTokenAPI = ServiceGenerator.createInterceptor(PostAPI.class);
    }

    public static PostRemoteDataSource getInstance() {
        if (instance == null)
            instance = new PostRemoteDataSource();
        return instance;
    }

    @Override
    public Observable<Response<PostResult>> getPostList(int category) {
        return postAPI.getPostList(category);
    }

    @Override
    public Observable<Response<PostCover>> uploadPostCover(PostCover cover) {

        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("title", toRequestBody(cover.getTitle()));
        requestBodyMap.put("start_date", toRequestBody(cover.getStartDate()));

        /*
         End Date 가 있으면 그대로 보내주고,
         없으면 보내지 않는다.
         */
        if(cover.getEndDate() != null){
            requestBodyMap.put("end_date", toRequestBody(cover.getEndDate()));
        }

        requestBodyMap.put("continent", toRequestBody(cover.getContinent()));
        /*
         Cover 사진이 있으면 그대로 보내주고,
         없으면 공백을 보내준다.
         */

        if(cover.getCoverPath()!= null){
            File file = new File(cover.getCoverPath());
            // create RequestBody instance from file
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            requestBodyMap.put("img\"; filename=\""+file.getName(), requestFile);
        }else{
            RequestBody requestFile = RequestBody.create(MediaType.parse(""),"");
            requestBodyMap.put("img\"; filename=\"", requestFile);
        }

        return postTokenAPI.uploadPostCover(requestBodyMap);
    }

    @Override
    public Observable<Response<PostContentResult>> getPostContentList(int postPk) {
        return postAPI.getPostContentList(postPk);
    }

    @Override
    public Observable<Response<Content>> uploadPostText(int postPk, UploadPostText postText) {
        return postTokenAPI.uploadPostText(postPk, postText);
    }

    public RequestBody toRequestBody(String json) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), json);
        return body;
    }
}
