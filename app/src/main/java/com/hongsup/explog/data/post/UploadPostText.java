package com.hongsup.explog.data.post;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Hong on 2017-12-14.
 */

public class UploadPostText implements Serializable{

    @SerializedName("content")
    private String content;
    @SerializedName("created_at")
    private String createdAt;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UploadPostText{" +
                "content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
