package com.hongsup.explog.data.sign;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Hong on 2017-11-29.
 */

public class SignIn {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
