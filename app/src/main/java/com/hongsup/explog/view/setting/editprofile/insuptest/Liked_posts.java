package com.hongsup.explog.view.setting.editprofile.insuptest;

/**
 * Created by 정인섭 on 2017-12-14.
 */

public class Liked_posts {
    private String id;

    private String author;

    private String num_liked;

    private String end_date;

    private String title;

    private String updated_at;

    private String[] liked;

    private String continent;

    private String created_at;

    private String start_date;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getNum_liked ()
    {
        return num_liked;
    }

    public void setNum_liked (String num_liked)
    {
        this.num_liked = num_liked;
    }

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

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String[] getLiked ()
    {
        return liked;
    }

    public void setLiked (String[] liked)
    {
        this.liked = liked;
    }

    public String getContinent ()
    {
        return continent;
    }

    public void setContinent (String continent)
    {
        this.continent = continent;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getStart_date ()
    {
        return start_date;
    }

    public void setStart_date (String start_date)
    {
        this.start_date = start_date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", author = "+author+", num_liked = "+num_liked+", end_date = "+end_date+", title = "+title+", updated_at = "+updated_at+", liked = "+liked+", continent = "+continent+", created_at = "+created_at+", start_date = "+start_date+"]";
    }
}
