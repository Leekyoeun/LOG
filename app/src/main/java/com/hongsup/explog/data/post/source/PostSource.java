package com.hongsup.explog.data.post.source;

import com.hongsup.explog.data.post.PostResult;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Android Hong on 2017-12-13.
 */

public interface PostSource {

    Observable<Response<PostResult>> getPostList(int category);
}
