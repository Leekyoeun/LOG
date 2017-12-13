package com.hongsup.explog.data.post;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class PostResult {

    @SerializedName("count")
    private int count;
    @SerializedName("results")
    private List<Post> PostList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Post> getPostList() {
        return PostList;
    }

    public void setPostList(List<Post> postList) {
        PostList = postList;
    }
}
