package com.hongsup.explog.view.gallery;

/**
 * Created by 정인섭 on 2017-11-30.
 */

public interface GalleryListener {

    /**
     * ImageView 클릭 시 발생하는 Listener
     *
     * @param position
     */
    void PhotoClick(int position);

    /**
     * ImageView 클릭 시 GalleryActivity View 변환하는 Listener
     */
    void changeView(int count);

    /**
     * ImageView 클릭 시 10개가 넘어갔을 때 Error 를 보여주는 Listener
     */
    void selectError();
}
