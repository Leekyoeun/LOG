package com.hongsup.explog.data.post.source;

import com.hongsup.explog.data.post.PostContentResult;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.post.PostResult;
import com.hongsup.explog.data.post.UploadCover;
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
    private PostAPI postTokenAPI;

    private PostRemoteDataSource() {
        // Service 생성
        postAPI = ServiceGenerator.create(PostAPI.class);
        postTokenAPI = ServiceGenerator.createInter(PostAPI.class);
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
    public Observable<Response<PostCover>> uploadPostCover(UploadCover cover) {
        return postTokenAPI.uploadPostCover(cover);
    }

    @Override
    public Observable<Response<PostContentResult>> getPostContentList(int postPk) {
        return postAPI.getPostContentList(postPk);
    }
}
