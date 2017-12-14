package com.hongsup.explog.view.posttext;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hongsup.explog.R;
import com.hongsup.explog.view.posttext.contract.PostTextContract;
import com.hongsup.explog.view.posttext.presenter.PostTextPresenter;
import com.hongsup.explog.view.posttext.view.PostTextView;

/**
 * Created by Hong on 2017-12-13.
 */

public class PostTextActivity extends AppCompatActivity {

    private PostTextContract.iView postTextView;
    private PostTextContract.iPresenter postTextPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postTextView = new PostTextView(this);
        postTextPresenter = new PostTextPresenter();

        postTextPresenter.attachView(postTextView);
        postTextView.setPresenter(postTextPresenter);

        setContentView(postTextView.getView());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        postTextView.setMenu(menu);
        postTextView.setTextChange();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        postTextView.onMenuClick(item);
        return true;
    }
}
