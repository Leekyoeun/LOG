package com.hongsup.explog.data.post.source;

import com.hongsup.explog.data.post.PostContentResult;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.post.PostResult;
import com.hongsup.explog.data.post.UploadCover;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Android Hong on 2017-12-13.
 */

public class PostRepository implements PostSource{

    private static PostRepository instance;

    private PostRemoteDataSource postRemoteDataSource;

    private PostRepository(){
        postRemoteDataSource = PostRemoteDataSource.getInstance();
    }

    public static PostRepository getInstance(){
        if(instance == null)
            instance = new PostRepository();
        return instance;
    }

    @Override
    public Observable<Response<PostResult>> getPostList(int category) {
        return postRemoteDataSource.getPostList(category);
    }

    @Override
    public Observable<Response<PostCover>> uploadPostCover(UploadCover cover) {
        return postRemoteDataSource.uploadPostCover(cover);
    }

    @Override
    public Observable<Response<PostContentResult>> getPostContentList(int postPk) {
        return postRemoteDataSource.getPostContentList(postPk);
    }
}
