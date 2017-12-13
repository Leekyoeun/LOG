package com.hongsup.explog.view.newspeeditem.adapter.contract;

import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.view.newspeeditem.listener.OnCoverClickListener;

import java.util.List;

/**
 * Created by Android Hong on 2017-12-13.
 */

public interface NewsPeedItemAdapterContract {

    interface iView{
        void notifyAdapter();
        void setOnItemClickListener(OnCoverClickListener listener);
    }

    interface iModel{
        void addItems(List<PostCover> postCoverList);
    }
}
