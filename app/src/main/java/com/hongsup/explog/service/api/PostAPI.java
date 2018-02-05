package com.hongsup.explog.service.api;

import com.hongsup.explog.data.post.Following;
import com.hongsup.explog.data.post.PostContent;
import com.hongsup.explog.data.post.PostContentResult;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.post.PostResult;
import com.hongsup.explog.data.post.Reply;
import com.hongsup.explog.view.search.insuptest.Word;
import com.hongsup.explog.view.setting.editprofile.insuptest.Following_users;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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
    Observable<Response<PostContent>> uploadPostText(@Path("post_pk") int postPk, @Field("content")String text, @Field("created_at")String date, @Field("type") String type);

    /**
     *  Upload Post Path
     */
    @FormUrlEncoded
    @POST("/post/{post_pk}/path/")
    Observable<Response<PostContent>> uploadPostPath(@Path("post_pk") int postPk, @Field("lat")String lat, @Field("lng")String lng);

    /**
     *  Upload Post Photo
     */
    @Multipart
    @POST("/post/{post_pk}/photo/")
    Observable<Response<PostContent>> uploadPostPhoto(@Path("post_pk") int postPk, @PartMap Map<String, RequestBody> postPhotoMap);

    /**
     * Post set Like
     * @param postPk
     * @return
     */
    @POST("/post/{post_pk}/like/")
    Observable<Response<PostCover>> setPostLike(@Path("post_pk") int postPk);

    @POST("/member/following/")
    Observable<Response<Following>> following(@Body Following following);

    @GET("/post/{post_pk}/reply/")
    Observable<Response<ArrayList<Reply>>> reply(@Path("post_pk") int postPk);

    @FormUrlEncoded
    @POST("/post/{post_pk}/reply/create/")
    Observable<Response<Reply>> reply_input(@Path("post_pk") int postPk, @Field("content") String content);

    @DELETE("/post/{post_pk}/update/")
    Observable<Response<Void>> delete(@Path("post_pk") int postPk);

}
