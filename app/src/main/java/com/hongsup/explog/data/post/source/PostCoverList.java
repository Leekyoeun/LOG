package com.hongsup.explog.data.post.source;

import com.google.gson.annotations.SerializedName;
import com.hongsup.explog.data.post.PostCover;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 정인섭 on 2018-01-16.
 */

public class PostCoverList {
    @SerializedName("posts")
    private ArrayList<PostCover> postCover;

    public ArrayList<PostCover> getPostCover() {
        return postCover;
    }

    public void setPostCover(ArrayList<PostCover> postCover) {
        this.postCover = postCover;
    }
}
