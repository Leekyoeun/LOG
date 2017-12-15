package com.hongsup.explog.view.cover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.hongsup.explog.data.photo.Photo;
import com.hongsup.explog.view.cover.contract.CoverContract;
import com.hongsup.explog.view.cover.presenter.CoverPresenter;
import com.hongsup.explog.view.cover.view.CoverView;

import java.util.ArrayList;

import static com.hongsup.explog.data.Const.REQ_GALLERY;

public class CoverActivity extends AppCompatActivity{

    private CoverContract.iView coverView;
    private CoverContract.iPresenter coverPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        coverView = new CoverView(this);
        coverPresenter = new CoverPresenter();

        coverPresenter.attachView(coverView);
        coverView.setPresenter(coverPresenter);

        setContentView(coverView.getView());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        coverView.setMenu(menu);
        coverView.setTextCount();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        coverView.onMenuClick(item);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_GALLERY:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        ArrayList<Photo> photoList = (ArrayList<Photo>) data.getSerializableExtra("PHOTO");
                        coverView.setCover(photoList.get(0).getImagePath());
                    }
                }
        }
    }
}
