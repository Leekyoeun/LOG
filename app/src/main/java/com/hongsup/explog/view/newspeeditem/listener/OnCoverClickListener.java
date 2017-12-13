package com.hongsup.explog.view.newspeeditem.listener;

import com.hongsup.explog.data.post.PostCover;

/**
 * Created by Android Hong on 2017-12-13.
 */

public interface OnCoverClickListener {

    /**
     * Apdater 에서 OnCoverClick 이 일어날 경우
     *
     * @param postCover
     */
    void onCoverClick(PostCover postCover);
}
