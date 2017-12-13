package com.hongsup.explog.data.post.source;

import com.hongsup.explog.data.post.PostResult;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.api.PostAPI;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Android Hong on 2017-12-13.
 */

public class PostRemoteDataSource implements PostSource{

    private static PostRemoteDataSource instance;

    private PostAPI postAPI;

    private PostRemoteDataSource() {
        // Service 생성
        postAPI = ServiceGenerator.create(PostAPI.class);
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
}
