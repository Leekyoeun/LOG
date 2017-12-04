package com.hongsup.explog.view.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hongsup.explog.view.sample.contract.SampleContract;
import com.hongsup.explog.view.sample.presenter.SamplePresenter;
import com.hongsup.explog.view.sample.view.SampleView;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SampleContract.iView mainView = new SampleView(this);
        SampleContract.iPresenter mainPresenter = new SamplePresenter();

        mainPresenter.attachView(mainView);
        mainView.setPresenter(mainPresenter);

        setContentView(mainView.getView());
    }
}
