package com.hongsup.explog.view.main.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.hongsup.explog.R;
import com.hongsup.explog.view.sample.adapter.SampleViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by Android Hong on 2017-12-05.
 */

public class NewsPeedView extends FrameLayout {

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    private Context context;

    public NewsPeedView(Context context) {
        super(context);
        this.context = context;
        initView();
        initListener();
    }

    public NewsPeedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.view_newspeed, null);
        ButterKnife.bind(this, view);
        SampleViewPagerAdapter sampleViewPagerAdapter = new SampleViewPagerAdapter(context);
        viewPager.setAdapter(sampleViewPagerAdapter);
        addView(view);
    }

    private void initListener() {

        /**
         * AppBarLayout 의 OffsetListener
         * Toolbar 의 LinearLayout 을 보여주거나,
         * 배경의 View 를 가려주는 역할을 한다.
         */
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(appBarLayout.getTotalScrollRange()) == Math.abs(verticalOffset)) {
                    // 같으면 Toolbar 에 있는 Layout 을 보여준다.
                    linearLayout.setVisibility(VISIBLE);
                } else {
                    // 다를 경우 Toolbar 에 있는 Layout 을 감춘다.
                    linearLayout.setVisibility(GONE);
                }

                // 밀려 올라가는 작업 해줘야 한다.

                // Alpha 조절하는 구역
                float ratio = (float) verticalOffset / (float) appBarLayout.getTotalScrollRange();
                constraintLayout.setAlpha(1 + ratio);
            }
        });

    }
}
