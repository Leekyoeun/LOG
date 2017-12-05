package com.hongsup.explog.view.sample.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.hongsup.explog.R;
import com.hongsup.explog.view.sample.adapter.SampleViewPagerAdapter;
import com.hongsup.explog.view.sample.contract.SampleContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class SampleView implements SampleContract.iView {

/*    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;*/
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private View view;
    private Context context;
    private SampleContract.iPresenter presenter;

    public SampleView(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.view_newspeed, null);
        ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)context).setSupportActionBar(toolbar);
        ((AppCompatActivity)context).getSupportActionBar().setTitle("");
        SampleViewPagerAdapter sampleViewPagerAdapter = new SampleViewPagerAdapter(context);
        viewPager.setAdapter(sampleViewPagerAdapter);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setPresenter(SampleContract.iPresenter presenter) {
        this.presenter = presenter;
    }

    /*
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
    */
}
