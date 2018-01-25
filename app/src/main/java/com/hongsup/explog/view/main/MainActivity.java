package com.hongsup.explog.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hongsup.explog.view.main.contract.MainContract;
import com.hongsup.explog.view.main.presenter.MainPresenter;
import com.hongsup.explog.view.main.view.MainView;

public class MainActivity extends AppCompatActivity {
    MainContract.iPresenter mainPresenter;
    MainContract.iView mainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainView = new MainView(this);
        mainPresenter = new MainPresenter();

        mainPresenter.attachView(mainView);
        mainView.setPresenter(mainPresenter);

        /* Animation 삭제 */
        overridePendingTransition(0,0);
        setContentView(mainView.getView());
    }
/*
    @Override
    protected void onRestart() {
        super.onResume();
        mainPresenter.refreshData(mainView);
    }*/

    @Override
    protected void onRestart() {
        super.onRestart();
        mainPresenter.refreshData(mainView);
        Log.d("MainonRestart()", "operation");
    }
}
