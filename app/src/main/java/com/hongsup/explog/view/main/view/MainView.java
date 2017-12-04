package com.hongsup.explog.view.main.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.hongsup.explog.R;
import com.hongsup.explog.view.main.adapter.MainViewPagerAdapter;
import com.hongsup.explog.view.main.contract.MainContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;

import static android.content.ContentValues.TAG;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class MainView implements MainContract.iView {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private View view;
    private Context context;
    private MainContract.iPresenter presenter;

    public MainView(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.activity_main, null);
        ButterKnife.bind(this, view);

        initView();

    }

    private void initView() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(context);
        viewPager.setAdapter(mainViewPagerAdapter);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setPresenter(MainContract.iPresenter presenter) {
        this.presenter = presenter;
    }


    float dY = 0;
    @OnTouch(R.id.viewPager)
    public boolean changeOnTouchView(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "changeOnTouchView: DOWN");

                Log.e(TAG, "changeOnTouchView: event.getRawY() " + event.getRawY());
                Log.e(TAG, "changeOnTouchView: viewPager.getY() " + viewPager.getY());
//                if (viewPager.getY() <= 0) {
//                    viewPager.setY(1);
//                }
                dY = viewPager.getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "changeOnTouchView: ACTION_MOVE");

                viewPager.animate()
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                //v.setY(event.getRawY()+dY);
                break;

            case MotionEvent.ACTION_UP:
//                if (relativeLayout.getY() <= relativeLayout.getHeight() / 2) {
//                    setAnimator(0);
//                } else {
//                    setAnimator(1);
//                }
                break;
        }
        return false;
    }
}
