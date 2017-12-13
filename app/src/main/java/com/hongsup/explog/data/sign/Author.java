package com.hongsup.explog.data.sign;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class Author {

    @SerializedName("pk")
    private int pk;
    @SerializedName("email")
    private String email;
    @SerializedName("img_profile")
    private String imgProfile;
    @SerializedName("username")
    private String userName;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(String imgProfile) {
        this.imgProfile = imgProfile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
