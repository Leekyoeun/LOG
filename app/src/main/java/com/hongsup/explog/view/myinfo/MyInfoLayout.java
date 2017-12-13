package com.hongsup.explog.view.myinfo;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hongsup.explog.R;
import com.hongsup.explog.view.myinfo.adapter.ViewPagerAdapter;
import com.hongsup.explog.view.setting.SettingActivity;

/**
 * Created by 정인섭 on 2017-12-06.
 */

public class MyInfoLayout extends FrameLayout {
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayoutMyinfo, tabLayoutClone;
    CoordinatorLayout coordinatorLayout;
    ImageView imgSetting;

    public MyInfoLayout(Context context) {
        super(context);

        init();
    }


    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_myinfo, this, false);
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager = view.findViewById(R.id.myinfo_viewPager);
        viewPager.setAdapter(viewPagerAdapter);
        imgSetting = view.findViewById(R.id.imgSetting);
        tabLayoutMyinfo = view.findViewById(R.id.tabLayoutMyInfo);
        //tabLayoutClone = view.findViewById(R.id.tabLayoutClone);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        setListener();

        addView(view);
    }

    private void setListener(){
        tabLayoutMyinfo.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        //tabLayoutClone.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutClone));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMyinfo));
        imgSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingActivity.class);
                getContext().startActivity(intent);
            }
        });

    }



}
