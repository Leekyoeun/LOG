package com.hongsup.explog.view.newspeed.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsup.explog.R;
import com.hongsup.explog.view.newspeed.adapter.NewsPeedViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Hong on 2017-12-05.
 */

public class NewsPeedView extends FrameLayout {

    // view_newspeed.xml 최상단 Layout
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    // view_newspeed.xml 하단 Content
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    // view_newspeed.xml AppBarLayout
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    // view_newspeed_toolbar.xml 최상단 Layout
    @BindView(R.id.toolbarLayout)
    LinearLayout toolbarLayout;
    // view_newspeed_toolbar.xml 에 들어가는 TabLayout
    @BindView(R.id.toolbarTabLayout)
    TabLayout toolbarTabLayout;

    // view_newspeed_headerder.xml 최상단 Layout
    @BindView(R.id.newsPeedTopLayout)
    ConstraintLayout newsPeedTopLayout;

    // view_newspeed_header.xmlxml 에 들어가는 TabLayout
    @BindView(R.id.topTabLayout)
    TabLayout topTabLayout;

    @BindView(R.id.textTopTitle)
    TextView textTopTitle;

    private Context context;

    public NewsPeedView(Context context) {
        super(context);
        this.context = context;
        initView();
        initTabLayout();
        initListener();
    }

    public NewsPeedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.view_newspeed, null);
        ButterKnife.bind(this, view);

        /**
         * ViewPager 설정
         */
        NewsPeedViewPagerAdapter newsPeedViewPagerAdapter = new NewsPeedViewPagerAdapter(context);
        viewPager.setAdapter(newsPeedViewPagerAdapter);
        viewPager.setOffscreenPageLimit(5);
        addView(view);
    }

    private void initTabLayout() {
        // TabLayout - ViewPager 연결
        toolbarTabLayout.setupWithViewPager(viewPager);
        topTabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < topTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = topTabLayout.getTabAt(i);

            /**
             * TabLayout Icon 설정부분
             */
            if (tab != null) {
                ImageView myCustomIcon = (ImageView) LayoutInflater.from(context).inflate(R.layout.item_tab, null);
                myCustomIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_australia));
                tab.setText("");
                tab.setCustomView(myCustomIcon);
            }
        }
    }


    private void initListener() {
        /**
         * ViewPager 의 index 가 0일때
         */
        if (viewPager.getCurrentItem() == 0) {
            String title = (String) toolbarTabLayout.getTabAt(0).getText();
            SpannableString content = new SpannableString(title);
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            textTopTitle.setText(content);
        }

        /**
         * ViewPager 리스너
         */
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                String title = (String) toolbarTabLayout.getTabAt(position).getText();
                SpannableString content = new SpannableString(title);
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                textTopTitle.setText(content);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


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
                    toolbarLayout.setVisibility(VISIBLE);
                } else {
                    // 다를 경우 Toolbar 에 있는 Layout 을 감춘다.
                    toolbarLayout.setVisibility(INVISIBLE);
                }

                // 밀려 올라가는 작업 해줘야 한다.c
                // Alpha 조절하는 구역
                float ratio = (float) verticalOffset / (float) appBarLayout.getTotalScrollRange();
                newsPeedTopLayout.setAlpha(1 + ratio);
            }
        });
    }
}
