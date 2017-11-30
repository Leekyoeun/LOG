package com.hongsup.explog.data.sign;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Hong on 2017-11-29.
 */

public class SignIn {

    @Expose(serialize = false, deserialize = false)
    private int code;

    @Expose(serialize = false, deserialize = true)
    private String token;

    @SerializedName("email")
    @Expose(serialize = true, deserialize = false)
    private String email;

    @SerializedName("password")
    @Expose(serialize = true, deserialize = false)
    private String password;

    @SerializedName("user")
    @Expose(serialize = false, deserialize = true)
    private User user;

    @SerializedName("message")
    @Expose(serialize = false, deserialize = true)
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SignIn{" +
                "code=" + code +
                ", token='" + token + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                ", message='" + message + '\'' +
                '}';
    }

    class User {

        @SerializedName("pk")
        @Expose(serialize = false, deserialize = true)
        private int pk;

        @SerializedName("email")
        @Expose(serialize = false, deserialize = true)
        private String email;

        @SerializedName("img_profile")
        @Expose(serialize = false, deserialize = true)
        private String img_profile;

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

        public String getImg_profile() {
            return img_profile;
        }

        public void setImg_profile(String img_profile) {
            this.img_profile = img_profile;
        }

        @Override
        public String toString() {
            return "User{" +
                    "pk=" + pk +
                    ", email='" + email + '\'' +
                    ", img_profile='" + img_profile + '\'' +
                    '}';
        }
    }

}
