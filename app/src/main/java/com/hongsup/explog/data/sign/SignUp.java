package com.hongsup.explog.data.sign;

import com.google.gson.annotations.Expose;

/**
 * Created by Android Hong on 2017-11-29.
 */

public class SignUp {

    @Expose(serialize = false, deserialize = false)
    private int code;

    @Expose(serialize = false, deserialize = true)
    private String pk;

    @Expose(serialize = true, deserialize = true)
    private String username;

    @Expose(serialize = true, deserialize = true)
    private String email;

    @Expose(serialize = true, deserialize = true)
    private String img_profile;

    @Expose(serialize = false, deserialize = true)
    private String token;

    @Expose(serialize = true, deserialize = true)
    private String password1;

    @Expose(serialize = true, deserialize = true)
    private String password2;

    @Expose(serialize = false, deserialize = true)
    private String non_field_errors;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
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

    public String getNon_field_errors() {
        return non_field_errors;
    }

    public void setNon_field_errors(String non_field_errors) {
        this.non_field_errors = non_field_errors;
    }

    @Override
    public String toString() {
        return "SignUp{" +
                "code=" + code +
                ", pk='" + pk + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", img_profile='" + img_profile + '\'' +
                ", token='" + token + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                ", non_field_errors='" + non_field_errors + '\'' +
                '}';
    }
}
