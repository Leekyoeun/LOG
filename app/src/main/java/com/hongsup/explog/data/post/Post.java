package com.hongsup.explog.data.post;

import com.google.gson.annotations.SerializedName;
import com.hongsup.explog.data.sign.Author;

import java.util.List;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class Post {

    @SerializedName("pk")
    private int pk;
    @SerializedName("Author")
    private Author author;
    @SerializedName("title")
    private String title;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("continent")
    private String continent;
    @SerializedName("post_content")
    private List<PostContent> postContentList;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public List<PostContent> getPostContentList() {
        return postContentList;
    }

    public void setPostContentList(List<PostContent> postContentList) {
        this.postContentList = postContentList;
    }

}
