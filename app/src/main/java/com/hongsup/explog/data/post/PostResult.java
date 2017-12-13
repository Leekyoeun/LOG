package com.hongsup.explog.data.post;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class PostResult {

    @SerializedName("count")
    private int count;
    @SerializedName("posts")
    private List<PostCover> PostList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PostCover> getPostList() {
        return PostList;
    }

    public void setPostList(List<PostCover> postList) {
        PostList = postList;
    }
}
