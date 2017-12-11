package com.hongsup.explog.data.post;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class PostContent {

    @SerializedName("post")
    private int post;
    @SerializedName("order")
    private int order;
    @SerializedName("content_type")
    private String contentType;

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
