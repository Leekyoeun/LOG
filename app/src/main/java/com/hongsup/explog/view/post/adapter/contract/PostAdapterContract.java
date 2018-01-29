package com.hongsup.explog.view.post.adapter.contract;

import com.hongsup.explog.data.post.PostContent;
import com.hongsup.explog.data.post.Reply;
import com.hongsup.explog.data.user.User;
import com.hongsup.explog.view.post.listener.OnPostContentClickListener;
import com.hongsup.explog.view.post.listener.OnPostFollowClickListener;
import com.hongsup.explog.view.post.listener.OnPostLikeClickListener;
import com.hongsup.explog.view.post.listener.OnReplyButtonClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Hong on 2017-12-14.
 */

public interface PostAdapterContract {

    interface iView {
        void setOnPostContentClickListener(OnPostContentClickListener postContentClickListener);

        void setOnPostLikeClickListener(OnPostLikeClickListener postLikeClickListener);

        void setOnPostFollowClickListener(OnPostFollowClickListener postFollowClickListener);

        void setOnReplyButtonClickListener(OnReplyButtonClickListener replyButtonClickListener);

        void notifyAllAdapter();

        void notifyLike(int position); // RecyclerView에서 특정 위치의 데이터가 바뀌었을 때 호출된다.
    }

    interface iModel {
        void setInit(int[] liked, int likeCount, User author);

        void setItems(List<PostContent> postContentList);

        void setLikeAndFollow(int[] liked, int likeCount, User author);

        void addItems(PostContent postContent, int order);

        void modifyLike(int position, int[] liked, int likeCount);

        void setCheckIfFollowing(boolean check);

        void setReply(int[] liked, int likeCount, User author, Reply reply);

        void setReplyInput(int[] liked, int likeCount, User author);

        void addReply(PostContent postContent);

        int getListSize();
    }
}
