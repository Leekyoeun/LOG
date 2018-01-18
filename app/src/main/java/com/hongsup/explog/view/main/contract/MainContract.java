package com.hongsup.explog.view.main.contract;


import android.view.View;

import com.hongsup.explog.view.myinfo.MyInfoLayout;

/**
 * Created by Android Hong on 2017-11-30.
 */

public interface MainContract{

    interface iView {
        View getView();
        void setPresenter(iPresenter presenter);
        MyInfoLayout getMyInfoLayout();
    }

    interface iPresenter{
        void attachView(iView view);
        void refreshData(iView view);
    }
}
