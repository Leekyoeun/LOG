package com.hongsup.explog.service.api;

import com.hongsup.explog.data.post.PostContent;
import com.hongsup.explog.data.post.PostContentResult;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.post.PostResult;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
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
     *
     * @param postCoverMap
     * @return
     */
    @Multipart
    @POST("/post/create/")
    Observable<Response<PostCover>> uploadPostCover(@PartMap Map<String, RequestBody> postCoverMap);

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
    @FormUrlEncoded
    @POST("/post/{post_pk}/text/")
    Observable<Response<PostContent>> uploadPostText(@Path("post_pk") int postPk, @Field("content")String text, @Field("created_at")String date);
}
