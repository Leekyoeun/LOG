package com.hongsup.explog.view.post.presenter;

import android.util.Log;

import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.data.post.PostContentResult;
import com.hongsup.explog.data.post.UploadPostText;
import com.hongsup.explog.data.post.source.PostRepository;
import com.hongsup.explog.view.post.adapter.contract.PostAdapterContract;
import com.hongsup.explog.view.post.contract.PostContract;
import com.hongsup.explog.view.post.listener.OnPostContentClickListener;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Android Hong on 2017-12-14.
 */

public class PostPresenter implements PostContract.iPresenter, OnPostContentClickListener {

    private int postPk;
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
    }

    @Override
    public void loadPostContent(int postPk) {
        // PK 설정
        this.postPk = postPk;
        view.showProgress();
        Observable<Response<PostContentResult>> observable = repository.getPostContentList(postPk);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                            if (data.isSuccessful()) {
                                if (data.code() == 200) {
                                    Log.e(TAG, "loadPostContent: 데이터 로드 완료");
                                    view.hideProgress();
                                    if (data.body().getPostContentList() == null || data.body().getPostContentList().size() == 0) {
                                        adapterModel.setInit();
                                    } else {
                                        adapterModel.addItems(data.body().getPostContentList());
                                    }
                                    /**
                                     * 마지막 Footer item 추가해야 한다.
                                     */
                                    adapterView.notifyAdapter();
                                } else {
                                    Log.e(TAG, "loadPostContent: 데이터 로드 실패");
                                    view.hideProgress();
                                }
                            } else {
                                Log.e(TAG, "loadPostContent: 데이터 로드 실패");
                                view.hideProgress();
                            }
                        },
                        throwable -> {
                            Log.e(TAG, "loadPostContent: 데이터 로드 실패");
                            view.hideProgress();
                        });
    }

    @Override
    public void uploadPostText(UploadPostText postText) {
        view.showProgress();
        Observable<Response<Content>> observable = repository.uploadPostText(postPk, postText);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                            if (data.isSuccessful()) {
                                if (data.code() == 200) {
                                    Log.e(TAG, "loadPostContent: 데이터 업로드 완료");
                                    view.hideProgress();

                                } else {
                                    Log.e(TAG, "loadPostContent: 데이터 업로드 실패");
                                    view.hideProgress();
                                }
                            } else {
                                Log.e(TAG, "loadPostContent: 데이터 업로드 실패");
                                view.hideProgress();
                            }
                        },
                        throwable -> {
                            Log.e(TAG, "loadPostContent: 데이터 업로드 실패");
                            view.hideProgress();
                        });
    }

    @Override
    public void uploadPostPath(double lat, double lng) {
        view.showProgress();
    }

    @Override
    public void uploadPostPhoto(String photoPath) {
        view.showProgress();
    }

}
