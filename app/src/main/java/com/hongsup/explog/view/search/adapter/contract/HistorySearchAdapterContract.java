package com.hongsup.explog.view.search.adapter.contract;

import com.hongsup.explog.view.search.listener.OnWordClickListener;

import java.util.List;

/**
 * Created by Hong on 2017-12-21.
 */

public interface HistorySearchAdapterContract {

    interface iView{
        void notifyAdapter();
        void setOnWordClickListener(OnWordClickListener listener);
    }

    interface iModel{
        void addItems(List<String> wordList);
    }
}
