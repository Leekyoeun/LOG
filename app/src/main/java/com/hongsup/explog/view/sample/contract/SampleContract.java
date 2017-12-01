package com.hongsup.explog.view.sample.contract;


import android.view.View;

/**
 * Created by Android Hong on 2017-11-30.
 */

public interface SampleContract {

    interface iView {
        View getView();
        void setPresenter(iPresenter presenter);
    }

    interface iPresenter{
        void attachView(iView view);
    }
}
