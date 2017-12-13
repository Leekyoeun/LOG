package com.hongsup.explog.data.sign;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Hong on 2017-11-29.
 */

public class SignUp {

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("img_profile")
    private String img_profile;

    @SerializedName("password")
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignUp{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", img_profile='" + img_profile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
