package com.hongsup.explog.view.search.adapter.contract;

import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.view.newspeeditem.listener.OnCoverClickListener;

import java.util.List;

/**
 * Created by Hong on 2017-12-21.
 */

public interface SearchResultAdapterContract {

    interface iView{
        void notifyAdapter();
        void setOnCoverClickListener(OnCoverClickListener listener);
    }

    interface iModel{
        void addItems(List<PostCover> searchResultList);
    }
}
