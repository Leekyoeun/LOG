package com.hongsup.explog.data.post;

import com.google.gson.annotations.SerializedName;
import com.hongsup.explog.data.user.User;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Android Hong on 2017-12-11.
 */
public class PostCover implements Serializable {

    @SerializedName("pk")
    private int pk;
    @SerializedName("author")
    private User author;
    @SerializedName("title")
    private String title;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("img")
    private String coverPath;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("continent")
    private String continent;
    @SerializedName("num_liked")
    private int likeCount;
    @SerializedName("liked")
    private int[] liked;

    // 오류 관련 필드
    @SerializedName("detail")
    private String detail;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
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

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int[] getLiked() {
        return liked;
    }

    public void setLiked(int[] liked) {
        this.liked = liked;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "PostCover{" +
                "pk=" + pk +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", coverPath='" + coverPath + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", continent='" + continent + '\'' +
                ", likeCount=" + likeCount +
                ", liked=" + Arrays.toString(liked) +
                ", detail='" + detail + '\'' +
                '}';
    }
}
