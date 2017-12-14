package com.hongsup.explog.data.post;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Hong on 2017-12-14.
 */

public class UploadCover {
    @SerializedName("title")
    private String title;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    /*
    @SerializedName("cover_path")
    private String coverPath;
    */
    @SerializedName("continent")
    private String continent;

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

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Override
    public String toString() {
        return "UploadCover{" +
                "title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", continent='" + continent + '\'' +
                '}';
    }
}


