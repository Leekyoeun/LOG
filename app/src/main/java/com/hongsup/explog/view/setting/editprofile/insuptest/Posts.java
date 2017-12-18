package com.hongsup.explog.view.setting.editprofile.insuptest;

/**
 * Created by 정인섭 on 2017-12-14.
 */

public class Posts {
    private String end_date;

    private String title;

    private String continent;

    private String created_at;

    private String start_date;

    private String pk;

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

    public String getPk ()
    {
        return pk;
    }

    public void setPk (String pk)
    {
        this.pk = pk;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [end_date = "+end_date+", title = "+title+", continent = "+continent+", created_at = "+created_at+", start_date = "+start_date+", pk = "+pk+"]";
    }
}
