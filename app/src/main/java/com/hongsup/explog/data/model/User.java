package com.hongsup.explog.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password1")
    @Expose
    private String password1;

    @SerializedName("password2")
    @Expose
    private String password2;

    @SerializedName("img_profile")
    @Expose
    private String img_profile;

    public User() {
    }

    public User(String username, String email, String password1, String password2, String img_profile) {
        this.username = username;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.img_profile = img_profile;
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

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getImg_profile() {
        return img_profile;
    }

    public void setImg_profile(String img_profile) {
        this.img_profile = img_profile;
    }
}