package com.hongsup.explog.data.post;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class PostContent implements Serializable{

    @SerializedName("post")
    private int post;
    @SerializedName("order")
    private int order;
    @SerializedName("content_type")
    private String contentType;
    @SerializedName("content")
    private Content content;

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

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PostContent{" +
                "post=" + post +
                ", order=" + order +
                ", contentType='" + contentType + '\'' +
                ", content=" + content +
                '}';
    }
}
