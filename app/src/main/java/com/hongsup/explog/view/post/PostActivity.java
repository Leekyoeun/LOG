package com.hongsup.explog.view.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.hongsup.explog.data.Const;
import com.hongsup.explog.data.post.UploadPostText;
import com.hongsup.explog.view.post.contract.PostContract;
import com.hongsup.explog.view.post.listener.OnPostContentClickListener;
import com.hongsup.explog.view.post.presenter.PostPresenter;
import com.hongsup.explog.view.post.view.PostView;

public class PostActivity extends AppCompatActivity implements OnPostContentClickListener {

    private static final String TAG = "PotsActivity";

    private PostContract.iView postView;
    private PostContract.iPresenter postPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        postView = new PostView(this);
        postPresenter = new PostPresenter();

        postPresenter.attachView(postView);
        postView.setPresenter(postPresenter);

        setContentView(postView.getView());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        postView.setMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        postView.onMenuClick(item);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Const.REQ_TEXT:
                if(resultCode == RESULT_OK){
                    UploadPostText postText = (UploadPostText)data.getSerializableExtra(Const.INTENT_EXTRA_CONTENT_TEXT);
                    int pk = data.getIntExtra(Const.INTENT_EXTRA_PK, -1);
                    postPresenter.uploadPostText(pk,postText);
                }
                break;
            case Const.REQ_GALLERY:
                if(resultCode == RESULT_OK){

                }
                break;
            case Const.REQ_PATH:
                if(resultCode == RESULT_OK){

                }
                break;
        }
    }
}
