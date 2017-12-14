package com.hongsup.explog.view.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.hongsup.explog.view.post.contract.PostContract;
import com.hongsup.explog.view.post.listener.OnPostContentClickListener;
import com.hongsup.explog.view.post.presenter.PostPresenter;
import com.hongsup.explog.view.post.view.PostView;

public class PostActivity extends AppCompatActivity implements OnPostContentClickListener {

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
        super.onActivityResult(requestCode, resultCode, data);
    }
}
