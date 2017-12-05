package com.hongsup.explog.view.main.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.hongsup.explog.R;
import com.hongsup.explog.view.sample.adapter.SampleViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Hong on 2017-12-05.
 */

class NewspeedView extends FrameLayout {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.appBar)
    AppBarLayout appbar;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private Context context;

    public NewspeedView(Context context) {
        super(context);
        this.context = context;
        initView();
        initListener();
    }

    public NewspeedView(Context context, @Nullable AttributeSet attrs) {
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
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(appBarLayout.getTotalScrollRange()) == Math.abs(verticalOffset)) {
                    linearLayout.setVisibility(VISIBLE);
                } else {
                    linearLayout.setVisibility(GONE);
                }
            }
        });

    }
}
