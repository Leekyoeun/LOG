package com.hongsup.explog.data.post;

/**
 * Created by 정인섭 on 2018-01-19.
 */

public class Following {
    private String from_user;
    private String to_user;
    private String unfollowing;


    public String getFrom_user() {
        return from_user;
    }

    public void setFrom_user(String from_user) {
        this.from_user = from_user;
    }

    public String getTo_user() {
        return to_user;
    }

    public void setTo_user(String to_user) {
        this.to_user = to_user;
    }

    public String getUnfollowing() {
        return unfollowing;
    }

    public void setUnfollowing(String unfollowing) {
        this.unfollowing = unfollowing;
    }
}
