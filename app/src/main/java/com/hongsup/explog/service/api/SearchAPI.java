package com.hongsup.explog.service.api;

import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.post.source.PostCoverList;
import com.hongsup.explog.view.search.insuptest.Word;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by 정인섭 on 2017-12-13.
 */

public interface SearchAPI {

    @POST("/post/search/")
    Observable<Response<PostCoverList>> observable(@Body Word word);
}
