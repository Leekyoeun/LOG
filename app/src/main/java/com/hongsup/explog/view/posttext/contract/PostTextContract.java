package com.hongsup.explog.view.posttext.contract;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Hong on 2017-12-14.
 */

public interface PostTextContract {

    interface iView{
        void setPresenter(iPresenter presenter);
        View getView();

        void setMenu(Menu menu);
        void onMenuClick(MenuItem item);
    }

    interface iPresenter{
        void attachView(iView view);
    }
}
