package com.hongsup.explog.view.newspeeditem.contract;

import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.view.newspeeditem.adapter.contract.NewsPeedItemAdapterContract;

/**
 * Created by Android Hong on 2017-12-13.
 */

public interface NewsPeedItemContract {

    interface iView{
        void setPresenter(iPresenter presenter);
        void goToPost(PostCover postCover);

        void showError(String text);
    }

    interface iPresenter{
        void attachView(iView view);
        void loadItem(int category);

        void setNewsPeedItemAdapterModel(NewsPeedItemAdapterContract.iModel model);
        void setNewsPeedItemAdapterView(NewsPeedItemAdapterContract.iView view);
    }
}
