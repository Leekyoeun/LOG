package com.hongsup.explog.view.setting.editprofile.insuptest;

/**
 * Created by 정인섭 on 2017-12-14.
 */

public class Posts {
    private String end_date;

    private String title;

    private String continent;

    private String start_date;

    private int pk;

    private String author;

    private String img;

    private int liked[];

    private int num_liked;



    public String getEnd_date ()
    {
        return end_date;
    }

    public void setEnd_date (String end_date)
    {
        this.end_date = end_date;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getContinent ()
    {
        return continent;
    }

    public void setContinent (String continent)
    {
        this.continent = continent;
    }

    public String getStart_date ()
    {
        return start_date;
    }

    public void setStart_date (String start_date)
    {
        this.start_date = start_date;
    }

    public int getPk ()
    {
        return pk;
    }

    public void setPk (int pk)
    {
        this.pk = pk;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int[] getLiked() {
        return liked;
    }

    public void setLiked(int[] liked) {
        this.liked = liked;
    }

    public int getNum_liked() {
        return num_liked;
    }

    public void setNum_liked(int num_liked) {
        this.num_liked = num_liked;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [end_date = "+end_date+", title = "+title+", continent = "+continent+", start_date = "+start_date+", pk = "+pk+"]";
    }
}
