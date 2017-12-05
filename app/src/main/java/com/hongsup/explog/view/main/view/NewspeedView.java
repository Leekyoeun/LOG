package com.hongsup.explog.view.main.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.hongsup.explog.R;
import com.hongsup.explog.view.sample.adapter.SampleViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by Android Hong on 2017-12-05.
 */

class NewspeedView extends FrameLayout {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.appBar)
    AppBarLayout appbar;
/*    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;*/

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
        View view = LayoutInflater.from(context).inflate(R.layout.activity_sample, null);
        ButterKnife.bind(this, view);
        SampleViewPagerAdapter sampleViewPagerAdapter = new SampleViewPagerAdapter(context);
        viewPager.setAdapter(sampleViewPagerAdapter);
        addView(view);
    }


    private void initListener() {
        //appbar.setMinimumHeight(linearLayout.getHeight());
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e(TAG, "onOffsetChanged.getTotalScrollRange() : " + appBarLayout.getTotalScrollRange() );
                Log.e(TAG, "onOffsetChanged: verticalOffset = " + verticalOffset );
                if (appBarLayout.getTotalScrollRange() == 0 || verticalOffset == 0) {

                    Log.e(TAG, "onOffsetChanged: 뿌");
                    //alphaTextView.setAlpha(0f);
                    //linearLayout.setVisibility(INVISIBLE);
                    return;
                }else{
                    //linearLayout.setVisibility(VISIBLE);
                }

                //float ratio = (float) verticalOffset / (float) appBarLayout.getTotalScrollRange();
                //alphaTextView.setAlpha(Math.abs(ratio));
            }
        });

        CollapsingToolbarLayout collapsingToolbarLayout;
    }
/*

    final TextView alphaTextView = (TextView) findViewById(R.id.textview);

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (appBarLayout.getTotalScrollRange() == 0 || verticalOffset == 0) {
                alphaTextView.setAlpha(0f);
                return;
            }

            float ratio = (float) verticalOffset / (float) appBarLayout.getTotalScrollRange();
            alphaTextView.setAlpha(Math.abs(ratio));
        }
    });*/
}
