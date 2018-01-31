package com.hongsup.explog.data.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hongsup.explog.view.setting.editprofile.insuptest.Followers;
import com.hongsup.explog.view.setting.editprofile.insuptest.Following_users;
import com.hongsup.explog.view.setting.editprofile.insuptest.Liked_posts;
import com.hongsup.explog.view.setting.editprofile.insuptest.Posts;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 정인섭 on 2017-12-11.
 *
 * Modify by Android Hong on 2017-12-13
 */

public class User implements Serializable{
    @SerializedName("pk")
    private int pk;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("img_profile")
    private String img_profile;
    @SerializedName("token")
    private String token;
//    @SerializedName("apnsdevice_set")
//    private Apnsdevice_set[] apnsdevice_set;


    /**
     * SignIn 오류 났을 경우
     */
    @SerializedName("message")
    @Expose(serialize = false, deserialize = true)
    private String message;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg_profile() {
        return img_profile;
    }

    public void setImg_profile(String img_profile) {
        this.img_profile = img_profile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public Apnsdevice_set[] getApnsdevice_set ()
//    {
//        return apnsdevice_set;
//    }
//
//    public void setApnsdevice_set (Apnsdevice_set[] apnsdevice_set)
//    {
//        this.apnsdevice_set = apnsdevice_set;
//    }



    @Override
    public String toString() {
        return "User{" +
                "pk=" + pk +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", img_profile='" + img_profile + '\'' +
                ", token='" + token + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
