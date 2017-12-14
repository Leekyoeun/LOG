package com.hongsup.explog.data.post.source;

import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.data.post.PostContentResult;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.post.PostResult;
import com.hongsup.explog.data.post.UploadCover;
import com.hongsup.explog.data.post.UploadPostText;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Android Hong on 2017-12-13.
 */

public interface PostSource {

    /**
     * 대륙별로 Post 가져오는 메소드
     * @param category
     * @return
     */
    Observable<Response<PostResult>> getPostList(int category);

    /**
     * Upload Cover 메소드
     * @param cover
     * @return
     */
    Observable<Response<PostCover>> uploadPostCover(UploadCover cover);

    /**
     * Post 에 대한 내용 가져오는 메소드
     *
     * @param postPk
     * @return
     */
    Observable<Response<PostContentResult>> getPostContentList(int postPk);

    /**
     * Post 에 대한 Text 추가하는 메소드
     * @param postPk
     * @param postText
     * @return
     */
    Observable<Response<Content>> uploadPostText(int postPk, UploadPostText postText);
}
