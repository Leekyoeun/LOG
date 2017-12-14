package com.hongsup.explog.service.api;

import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.data.post.PostContent;
import com.hongsup.explog.data.post.PostContentResult;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.post.PostResult;
import com.hongsup.explog.data.post.UploadCover;
import com.hongsup.explog.data.post.UploadPostText;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Android Hong on 2017-12-13.
 */

public interface PostAPI {

    /**
     * 카테고리 대륙별로 가져오는 메소드
     * @param category
     * @return
     */
    @GET("/post/{category}/list/")
    Observable<Response<PostResult>> getPostList(@Path("category")int category);

    /**
     * Upload Cover
     * @param cover
     * @return
     */
    @POST("/post/create/")
    Observable<Response<PostCover>> uploadPostCover(@Body UploadCover cover);

    /**
     * Post 에 대한 내용 가져오는 메소드
     * @param postPk
     * @return
     */
    @GET("/post/{post_pk}")
    Observable<Response<PostContentResult>> getPostContentList(@Path("post_pk") int postPk);

    /**
     *  Upload Post Text
     */
    @POST("/post/{post_pk}/text/")
    Observable<Response<Content>> uploadPostText(@Path("post_pk") int postPk, @Body UploadPostText uploadPostText);
}
