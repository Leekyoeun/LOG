package com.hongsup.explog.view.setting.editprofile.insuptest;

/**
 * Created by 정인섭 on 2017-12-14.
 */

public class Following_users {
    private String username;

    private String img_profile;

    private String email;

    private String pk;

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getImg_profile ()
    {
        return img_profile;
    }

    public void setImg_profile (String img_profile)
    {
        this.img_profile = img_profile;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
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
        return "ClassPojo [username = "+username+", img_profile = "+img_profile+", email = "+email+", pk = "+pk+"]";
    }
}
