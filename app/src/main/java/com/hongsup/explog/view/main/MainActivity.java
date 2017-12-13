package com.hongsup.explog.view.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hongsup.explog.view.main.contract.MainContract;
import com.hongsup.explog.view.main.presenter.MainPresenter;
import com.hongsup.explog.view.main.view.MainView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainContract.iView mainView = new MainView(this);
        MainContract.iPresenter mainPresenter = new MainPresenter();

        mainPresenter.attachView(mainView);
        mainView.setPresenter(mainPresenter);

        /* Animation 삭제 */
        overridePendingTransition(0,0);
        setContentView(mainView.getView());
    }
}
