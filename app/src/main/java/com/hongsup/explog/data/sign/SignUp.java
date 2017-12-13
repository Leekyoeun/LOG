package com.hongsup.explog.data.sign;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Hong on 2017-11-29.
 */

public class SignUp {

//    @Expose(serialize = false, deserialize = false)
//    private int code;
//
//    @Expose(serialize = false, deserialize = true)
//    private String pk;

    //@Expose(serialize = true, deserialize = true)
    @SerializedName("username")
    private String username;

    //@Expose(serialize = true, deserialize = true)
    @SerializedName("email")
    private String email;

    //@Expose(serialize = true, deserialize = false)
    @SerializedName("img_profile")
    private String img_profile;

//    @Expose(serialize = false, deserialize = true)
//    private String token;

    //백엔드쪽 데이터 파라미터 수정에 의해 password1, password2가 password 하나로 합쳐짐 12/5
    //@Expose(serialize = true, deserialize = true)
    @SerializedName("password")
    private String password;

//    @Expose(serialize = false, deserialize = true)
//    private String non_field_errors;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getPk() {
//        return pk;
//    }
//
//    public void setPk(String pk) {
//        this.pk = pk;
//    }

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

//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }

    //백엔드쪽 데이터 파라미터 수정에 의해 password1, password2가 password 하나로 합쳐짐 12/5
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getNon_field_errors() {
//        return non_field_errors;
//    }
//
//    public void setNon_field_errors(String non_field_errors) {
//        this.non_field_errors = non_field_errors;
//    }

    //백엔드쪽 데이터 파라미터 수정에 의해 password1, password2가 password 하나로 합쳐짐
    @Override
    public String toString() {
        return "SignUp{" +
                //"code=" + code +
                //", pk='" + pk + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", img_profile='" + img_profile + '\'' +
                //", token='" + token + '\'' +
                ", password='" + password + '\'' +
                //", non_field_errors='" + non_field_errors + '\'' +
                '}';
    }
}
