package com.hongsup.explog.view.search.insuptest;


import com.hongsup.explog.data.user.User;

/**
 * Created by 정인섭 on 2017-12-13.
 */

public class SearchResponse {

    private String pk;
    private String num_liked;
    private User author;
    private String end_date;
    private String title;
    private String[] liked;
    private String continent;
    private String start_date;

    public String getNum_liked() {
        return num_liked;
    }

    public void setNum_liked(String num_liked) {
        this.num_liked = num_liked;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getLiked() {
        return liked;
    }

    public void setLiked(String[] liked) {
        this.liked = liked;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    @Override
    public String toString() {
        return "ClassPojo [num_liked = " + num_liked + ", author = " + author + ", end_date = " + end_date + ", title = " + title + ", liked = " + liked + ", continent = " + continent + ", start_date = " + start_date + ", pk = " + pk + "]";
    }
}
