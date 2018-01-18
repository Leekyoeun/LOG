package com.hongsup.explog.view.setting.editprofile.insuptest;

import com.hongsup.explog.data.post.PostCover;

import java.util.ArrayList;

/**
 * Created by 정인섭 on 2017-12-14.
 */

public class UserInformation {
    private Followers[] followers;

    private String username;

    private String follower_number;

    private String following_number;

    private String img_profile;

    private String email;

    private ArrayList<Liked_posts> liked_posts;

    private ArrayList<Posts> posts;

    private Following_users[] following_users;

    private String pk;


    public Followers[] getFollowers ()
    {
        return followers;
    }

    public void setFollowers (Followers[] followers)
    {
        this.followers = followers;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getFollower_number ()
    {
        return follower_number;
    }

    public void setFollower_number (String follower_number)
    {
        this.follower_number = follower_number;
    }

    public String getFollowing_number ()
    {
        return following_number;
    }

    public void setFollowing_number (String following_number)
    {
        this.following_number = following_number;
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

    public ArrayList<Liked_posts> getLiked_posts ()
    {
        return liked_posts;
    }

    public void setLiked_posts (ArrayList<Liked_posts> liked_posts)
    {
        this.liked_posts = liked_posts;
    }

    public ArrayList<Posts> getPosts ()
    {
        return posts;
    }

    public void setPosts (ArrayList<Posts> posts)
    {
        this.posts = posts;
    }

    public Following_users[] getFollowing_users ()
    {
        return following_users;
    }

    public void setFollowing_users (Following_users[] following_users)
    {
        this.following_users = following_users;
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
        return "ClassPojo [followers = "+followers+", username = "+username+", follower_number = "+follower_number+", following_number = "+following_number+", img_profile = "+img_profile+", email = "+email+", liked_posts = "+liked_posts+", posts = "+posts+", following_users = "+following_users+", pk = "+pk+"]";
    }

}
