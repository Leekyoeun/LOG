package com.hongsup.explog.service.api;

import com.hongsup.explog.data.post.PostResult;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Android Hong on 2017-12-13.
 */

public interface PostAPI {

    @GET("/post/{category}/list/")
    Observable<Response<PostResult>> getPostList(@Path("category")int category);
}
