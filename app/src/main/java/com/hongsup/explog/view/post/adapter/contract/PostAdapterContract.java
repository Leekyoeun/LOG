package com.hongsup.explog.view.post.adapter.contract;

import com.hongsup.explog.data.post.PostContent;
import com.hongsup.explog.data.user.User;
import com.hongsup.explog.view.post.listener.OnPostContentClickListener;
import com.hongsup.explog.view.post.listener.OnPostLikeClickListener;

import java.util.List;

/**
 * Created by Android Hong on 2017-12-14.
 */

public interface PostAdapterContract {

    interface iView {
        void setOnPostContentClickListener(OnPostContentClickListener postContentClickListener);

        void setOnPostLikeClickListener(OnPostLikeClickListener postLikeClickListener);

        void notifyAllAdapter();

        void notifyLike(int position);
    }

    interface iModel {
        void setInit(int[] liked, int likeCount, User author);

        void setItems(List<PostContent> postContentList);

        void setLikeAndFollow(int[] liked, int likeCount, User author);

        void addItems(PostContent postContent);

        void modifyLike(int position, int[] liked, int likeCount);
    }
}
