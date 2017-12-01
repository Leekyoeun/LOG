package com.hongsup.explog.view.sample.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.hongsup.explog.view.sample.view.SampleSubView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class SampleViewPagerAdapter extends PagerAdapter{

    private static final int COUNT = 4;
    List<View> views;

    public SampleViewPagerAdapter(Context context) {
        View view = new SampleSubView(context);

        views = new ArrayList<>();
        views.add(new SampleSubView(context));
        views.add(new SampleSubView(context));
        views.add(new SampleSubView(context));
        views.add(new SampleSubView(context));
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View)object);
    }

    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

}