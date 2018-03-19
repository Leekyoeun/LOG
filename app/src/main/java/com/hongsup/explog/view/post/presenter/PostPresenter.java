package com.hongsup.explog.view.post.presenter;

import android.util.Log;

import com.hongsup.explog.data.Const;
import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.data.post.Following;
import com.hongsup.explog.data.post.PostContent;
import com.hongsup.explog.data.post.PostContentResult;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.post.Reply;
import com.hongsup.explog.data.post.source.PostRepository;
import com.hongsup.explog.data.user.User;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.api.PostAPI;
import com.hongsup.explog.view.post.adapter.contract.PostAdapterContract;
import com.hongsup.explog.view.post.contract.PostContract;
import com.hongsup.explog.view.post.listener.OnPostContentClickListener;
import com.hongsup.explog.view.post.listener.OnPostFollowClickListener;
import com.hongsup.explog.view.post.listener.OnPostLikeClickListener;
import com.hongsup.explog.view.post.listener.OnReplyButtonClickListener;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Android Hong on 2017-12-14.
 */

public class PostPresenter implements PostContract.iPresenter, OnPostContentClickListener, OnPostLikeClickListener, OnPostFollowClickListener, OnReplyButtonClickListener {

    private int postPk;
    private int userPk;
    private PostContract.iView view;
    private PostRepository repository;
    private PostAdapterContract.iView adapterView;
    private PostAdapterContract.iModel adapterModel;

    public PostPresenter() {
        repository = PostRepository.getInstance();
    }

    @Override
    public void attachView(PostContract.iView view) {
        this.view = view;
    }

    @Override
    public void setPostAdapterModel(PostAdapterContract.iModel model) {
        this.adapterModel = model;
    }

    @Override
    public void setPostAdapterView(PostAdapterContract.iView view) {
        this.adapterView = view;
        this.adapterView.setOnPostContentClickListener(this);
        this.adapterView.setOnPostLikeClickListener(this);
        this.adapterView.setOnPostFollowClickListener(this);
        this.adapterView.setOnReplyButtonClickListener(this);
    }

    ArrayList<User> userList;

    public void loadFollowing(ArrayList<User> list){
        //ArrayList를 받는다.
        userList = list;
    }

    @Override
    public void loadPostContent(PostCover cover) {
        // PK 설정
        this.postPk = cover.getPk();
        this.userPk = cover.getAuthor().getPk();
        view.showProgress();
        Observable<Response<PostContentResult>> observable = repository.getPostContentList(postPk);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                            if (data.isSuccessful()) {
                                boolean checkIfFollowing = false;
                                Log.e(TAG, "loadPostContent: 데이터 로드 완료");
                                view.hideProgress();
                                //list = loadReply(cover);

                                if (data.body().getPostContentList() == null || data.body().getPostContentList().size() == 0) {
                                    adapterModel.setInit(cover.getLiked(), cover.getLikeCount(), cover.getAuthor());
                                } else {
                                    adapterModel.setItems(data.body().getPostContentList());
                                    adapterModel.setLikeAndFollow(cover.getLiked(), cover.getLikeCount(), cover.getAuthor());
                                }

                                loadReply(cover);

                                Log.d("PostPresenter", list.size()+"");
                                adapterView.notifyAllAdapter();

                                for(User user : userList){
                                    if(user.getEmail().equals(cover.getAuthor().getEmail())){
                                        checkIfFollowing = true;
                                        Log.d("loadPostContent", user.getEmail());
                                        break;
                                    }else{
                                        Log.d("loadPostContent", "false가 뜬다");
                                        checkIfFollowing = false;
                                    }
                                }
                                adapterModel.setCheckIfFollowing(checkIfFollowing);

                                Log.d("loadPostContent", checkIfFollowing + "");


                            } else {
                                Log.e(TAG, "loadPostContent: 데이터 로드 실패1");
                                view.hideProgress();
                            }
                        },
                        throwable -> {
                            Log.e(TAG, "loadPostContent: 데이터 로드 실패2");
                            Log.e(TAG, throwable.getMessage());
                            view.hideProgress();
                        });
    }
    ArrayList<Reply> list = new ArrayList<>();

    public void loadReply(PostCover cover){

        this.postPk = cover.getPk();
        PostAPI postAPI = ServiceGenerator.createInterceptor(PostAPI.class);
        Observable<Response<ArrayList<Reply>>> observable = postAPI.reply(postPk);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data->{
                    if(data.isSuccessful()){
                     Log.e(TAG, "loadReply() 로드 완료 why?");
                     list = data.body();
                        for(int i=0; i<list.size(); i++){
                            adapterModel.setReply(cover.getLiked(), cover.getLikeCount(), cover.getAuthor(), list.get(i));
                        }
                        adapterModel.setReplyInput(cover.getLiked(), cover.getLikeCount(), cover.getAuthor());


                    }else{
                        Log.e(TAG, data.errorBody().string());
                    }
                }, throwable -> {
                    Log.e(TAG, throwable.getMessage());
                });
    }

//    public void loadFollowInfo(){
//        EditProfileAPI profileEditAPI = ServiceGenerator.createInterceptor(EditProfileAPI.class);
//        Observable<Response<UserInformation>> getUserInfo = profileEditAPI.getUserInfo();
//        getUserInfo.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(data -> {
//                    if (data.isSuccessful()) {
//                        if (data.code() == 200) {
//                            Log.d("PostPresenter", "확인됨");
//
//                            userList = data.body().getFollowing_users();
//
//
//                        } else {
//                            Log.d("PostPresenter", "확인안됨");
//                        }
//                    } else {
//                        Log.d("PostPresenter", data.errorBody().string() + "data unsuccessful");
//                    }
//                }, throwable -> {
//                    Log.e("PostPresenter", throwable.getMessage());
//                });
//    }

    @Override
    public void uploadPostText(String text, String date) {
        view.showProgress();
        Observable<Response<PostContent>> observable = repository.uploadPostText(postPk, text, date, "b");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                            Log.e(TAG, "uploadPostText: " + data.code() + ", " + data.message());
                            if (data.isSuccessful()) {
                                Log.e(TAG, "uploadPostText: 데이터 업로드 완료");
                                view.hideProgress();
                                adapterModel.addItems(data.body(), data.body().getOrder());
                                adapterView.notifyAllAdapter();
                            } else {
                                Log.e(TAG, "uploadPostText: 데이터 업로드 실패1");
                                Log.e(TAG, data.errorBody().string());
                                view.hideProgress();
                            }
                        },
                        throwable -> {
                            Log.e(TAG, "uploadPostText: 데이터 업로드 실패2");
                            view.hideProgress();
                            Log.e(TAG, "uploadPostText: " + throwable.getMessage());
                        });
    }

    @Override
    public void uploadPostPath(double lat, double lng) {
        view.showProgress();
        Observable<Response<PostContent>> observable = repository.uploadPostPath(postPk, lat, lng);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                            Log.e(TAG, "uploadPostPath: " + data.code() + ", " + data.message());
                            if (data.isSuccessful()) {
                                Log.e(TAG, "uploadPostPath: 데이터 업로드 완료");
                                view.hideProgress();
                                adapterModel.addItems(data.body(), data.body().getOrder());
                            } else {
                                Log.e(TAG, "uploadPostPath: 데이터 업로드 실패1");
                                view.hideProgress();
                            }
                        },
                        throwable -> {
                            Log.e(TAG, "uploadPostPath: 데이터 업로드 실패2");
                            view.hideProgress();
                            Log.e(TAG, "uploadPostPath: " + throwable.getMessage());
                        });
    }

    @Override
    public void uploadPostPhoto(String photoPath) {
        view.showProgress();
        Observable<Response<PostContent>> observable = repository.uploadPostPhoto(postPk, photoPath);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                            Log.e(TAG, "uploadPostPhoto: " + data.code() + ", " + data.message());
                            if (data.isSuccessful()) {
                                Log.e(TAG, "uploadPostPhoto: 데이터 업로드 완료");
                                view.hideProgress();
                                adapterModel.addItems(data.body(), data.body().getOrder());
                            } else {
                                Log.e(TAG, "uploadPostPhoto: 데이터 업로드 실패1");
                                view.hideProgress();
                            }
                        },
                        throwable -> {
                            Log.e(TAG, "uploadPostPhoto: 데이터 업로드 실패2");
                            view.hideProgress();
                            Log.e(TAG, "uploadPostPhoto: " + throwable.getMessage());
                        });
    }

    public void uploadReply(){

    }

    @Override
    public void setOnLikeClick(int position) {
        view.showProgress();
        Observable<Response<PostCover>> observable = repository.setPostLike(postPk);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                            Log.e(TAG, "setOnLikeClick: " + data.code() + ", " + data.message());
                            if (data.isSuccessful()) {
                                Log.e(TAG, "setOnLikeClick: '좋아요' 완료");
                                view.hideProgress();
                                adapterModel.modifyLike(position, data.body().getLiked(), data.body().getLikeCount()); // DB를 새로운 데이터로 갱신한다.
                                adapterView.notifyLike(position);//특정 위치의 데이터가 업데이트 되었을 때 호출하여 view를 업데이트 한다.
                            } else {
                                Log.e(TAG, "setOnLikeClick: '좋아요' 실패1");
                                view.hideProgress();
                            }
                        },
                        throwable -> {
                            Log.e(TAG, "setOnLikeClick: '좋아요' 실패2");
                            view.hideProgress();
                            Log.e(TAG, "setOnLikeClick: " + throwable.getMessage());
                        });
    }

    @Override
    public void setOnFollowClick() {
        view.showProgress();
        Following follow = new Following();
        follow.setTo_user(String.valueOf(userPk));
        PostAPI postApi = ServiceGenerator.createInterceptor(PostAPI.class);
        Observable<Response<Following>> following = postApi.following(follow);
        following.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data->{
                    if(data.isSuccessful()){
                        Log.d("FollowClick", "눌리니?" + "   " + userPk);
                        //Log.d("FollowClick", data.body().getFrom_user());
                        view.hideProgress();


                    }else{
                        Log.d("FollowClick", "실패?" + "   " + userPk);
                        //Log.d("FollowClick", data.body().getFrom_user() + " " + data.body().getTo_user());
                        Log.d("FollowClick", data.errorBody().string());
                    }
                }, throwable -> {
                    Log.e(TAG, "FollowClick: " + throwable.getMessage());
                });
    }

    @Override
    public void setOnReplyClick(String content) {
        PostAPI postAPI = ServiceGenerator.createInterceptor(PostAPI.class);
        Observable<Response<Reply>> reply_input = postAPI.reply_input(postPk, content);
        reply_input.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data->{
                    if(data.isSuccessful()){
                        Log.d("setOnReplyClick()", "잘 들어옵니다");
                        PostContent postContent = new PostContent();
                        Content content1 = new Content();
                        content1.setReply(data.body());
                        postContent.setContent(content1);
                        postContent.setContentType(Const.CONTENT_TYPE_REPLY);
                        adapterModel.addReply(postContent);
                        view.recyclerDown(adapterModel.getListSize());

                    }else{
                        Log.d("setOnReplyClick()", data.errorBody().string());
                    }

                }, throwable -> {
                    Log.d("setOnReplyClick()", throwable.getMessage());

                });
    }

    @Override
    public void setOnPostDelete(){
        PostAPI postAPI = ServiceGenerator.createInterceptor(PostAPI.class);
        Observable<Response<Void>> delete = postAPI.delete(postPk);
        delete.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data->{
                    Log.d("PostPresenter", "setOnPostDelete() work");

                }, throwable -> {
                    Log.d("PostPresenter", throwable.toString());

                });
    }
}
