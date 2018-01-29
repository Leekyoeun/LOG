package com.hongsup.explog.view.post.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongsup.explog.data.Const;
import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.data.post.PostContent;
import com.hongsup.explog.data.post.Reply;
import com.hongsup.explog.data.user.User;
import com.hongsup.explog.view.post.adapter.contract.PostAdapterContract;
import com.hongsup.explog.view.post.adapter.viewholder.PostViewHolder;
import com.hongsup.explog.view.post.listener.OnPostContentClickListener;
import com.hongsup.explog.view.post.listener.OnPostFollowClickListener;
import com.hongsup.explog.view.post.listener.OnPostLikeClickListener;
import com.hongsup.explog.view.post.listener.OnReplyButtonClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Hong on 2017-12-11.
 */

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> implements PostAdapterContract.iModel, PostAdapterContract.iView {

    private static final String TAG = "PostAdapter";

    private Context context;
    private List<PostContent> postContentList;
    private OnPostContentClickListener postContentClickListener;
    private OnPostLikeClickListener postLikeClickListener;
    private OnPostFollowClickListener postFollowClickListener;
    private OnReplyButtonClickListener replyButtonClickListener;
    private boolean checkMyPost;
    private boolean checkIfFollowing;

    public PostAdapter(Context context, boolean checkMyPost) {
        postContentList = new ArrayList<>();
        this.context = context;
        this.checkMyPost = checkMyPost;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = PostViewHolderFactory.getPostItemLayoutId(viewType);
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return PostViewHolderFactory.getViewHolder(viewType, view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        PostContent postContent = postContentList.get(position);
        holder.setContext(context);
        holder.setPosition(position);
        holder.setContentClickListener(postContentClickListener);
        holder.setLikeClickListener(postLikeClickListener);
        holder.setPostFollowClickListener(postFollowClickListener);
        holder.setReplyButtonClickListener(replyButtonClickListener);
        holder.setCheckMyPost(checkMyPost);
        holder.setCheckFollowing(checkIfFollowing);
        holder.bind(postContent.getContent());
    }

    @Override
    public int getItemCount() {
        return postContentList.size();
    }

    @Override
    public int getItemViewType(int position) {
        PostContent postContent = postContentList.get(position);
        if (Const.CONTENT_TYPE_TEXT.equals(postContent.getContentType())) {
            return Const.VIEW_TYPE_TEXT;
        } else if (Const.CONTENT_TYPE_PHOTO.equals(postContent.getContentType())) {
            return Const.VIEW_TYPE_PHOTO;
        } else if (Const.CONTENT_TYPE_PATH.equals(postContent.getContentType())) {
            return Const.VIEW_TYPE_PATH;
        } else if (Const.CONTENT_TYPE_INIT.equals(postContent.getContentType())) {
            return Const.VIEW_TYPE_INIT;
        } else if (Const.CONTENT_TYPE_FOOTER.equals(postContent.getContentType())) {
            return Const.VIEW_TYPE_FOOTER;
        } else if (Const.CONTENT_TYPE_REPLY.equals(postContent.getContentType())) {
            return Const.VIEW_TYPE_REPLY;
        } else if (Const.CONTENT_TYPE_REPLY_INPUT.equals(postContent.getContentType())) {
            return Const.VIEW_TYPE_REPLY_INPUT;
        }
        throw new RuntimeException("there is no type that matches the type " + postContent.getContentType() + " + make sure your using types correctly");
    }

    @Override
    public void setOnPostContentClickListener(OnPostContentClickListener postContentClickListener) {
        this.postContentClickListener = postContentClickListener;
    }

    @Override
    public void setOnPostLikeClickListener(OnPostLikeClickListener postLikeClickListener) {
        this.postLikeClickListener = postLikeClickListener;
    }

    @Override
    public void setOnPostFollowClickListener(OnPostFollowClickListener postFollowClickListener) {
        this.postFollowClickListener = postFollowClickListener;
    }

    @Override
    public void setOnReplyButtonClickListener(OnReplyButtonClickListener replyButtonClickListener) {
        this.replyButtonClickListener = replyButtonClickListener;
    }

    @Override
    public void notifyAllAdapter() {
        notifyItemRangeChanged(0, postContentList.size());
    }

    @Override
    public void notifyLike(int position) {
        Log.d("what is position", position + "");
        notifyItemChanged(position);
    }

    @Override
    public void setInit(int[] liked, int likeCount, User author) {
        postContentList.add(createContent(liked, likeCount, author, Const.CONTENT_TYPE_INIT));
    }

    @Override
    public void setCheckIfFollowing(boolean check) {
        this.checkIfFollowing = check;
        Log.d("setCheckIfFollowing", "follow me");
    }

    @Override
    public void setLikeAndFollow(int[] liked, int likeCount, User author) {
        postContentList.add(createContent(liked, likeCount, author, Const.CONTENT_TYPE_FOOTER));
    }

    @Override
    public void setReply(int[] liked, int likeCount, User author, Reply reply) {
        postContentList.add(createContent(liked, likeCount, author, Const.CONTENT_TYPE_REPLY, reply));
    }

    @Override
    public void setReplyInput(int[] liked, int likeCount, User author){
        postContentList.add(createContent(liked, likeCount, author, Const.CONTENT_TYPE_REPLY_INPUT));
        notifyItemRangeChanged(0, postContentList.size());

    }

    @Override
    public void setItems(List<PostContent> postContentList) {
        this.postContentList.clear();
        this.postContentList = postContentList;
    }

    @Override
    public void addItems(PostContent postContent, int order) {
        if (postContentList.get(0).getContentType().equals(Const.CONTENT_TYPE_INIT)) {
            // 첫번째 아이템이 init 인 경우
            PostContent footerContent = createContent(postContentList.get(0).getContent().getLiked(), postContentList.get(0).getContent().getLikeCount(), postContentList.get(0).getContent().getAuthor(), Const.CONTENT_TYPE_FOOTER);
            this.postContentList.clear();
            this.postContentList.add(postContent);
            this.postContentList.add(footerContent);
            notifyItemRangeChanged(0, postContentList.size());
        } else {
            // 아닌 경우
            Log.d(TAG, "addItems() 실행");
            this.postContentList.add(order-1, postContent);
            notifyItemInserted(order-1);
        }
    }

    @Override
    public void addReply(PostContent postContent){
        this.postContentList.add(postContentList.size()-1, postContent);
        notifyItemInserted(postContentList.size()-1);
    }

    @Override
    public void modifyLike(int position, int[] liked, int likeCount) {
        postContentList.get(position).getContent().setLikeCount(likeCount);
        postContentList.get(position).getContent().setLiked(liked);
    }

    public int getListSize(){
        return postContentList.size()-1;
    }

    /**
     * initViewHolder Item OR FooterViewHolder Item 생성기
     *
     * @param likeCount
     * @param author
     * @param type
     * @return
     */
    private PostContent createContent(int[] liked, int likeCount, User author, String type) {
        PostContent postContent = new PostContent();

        /**
         * Like 와 Author 설정
         */
        Content content = new Content();
        content.setLikeCount(likeCount);
        content.setLiked(liked);
        content.setAuthor(author);

        postContent.setContent(content);

        postContent.setContentType(type);

        return postContent;
    }

    private PostContent createContent(int[] liked, int likeCount, User author, String type, Reply reply) {
        PostContent postContent = new PostContent();

        /**
         * Like 와 Author 설정
         */
        Content content = new Content();
        content.setLikeCount(likeCount);
        content.setLiked(liked);
        content.setAuthor(author);
        content.setReply(reply);
        postContent.setContent(content);

        postContent.setContentType(type);

        return postContent;
    }


}
