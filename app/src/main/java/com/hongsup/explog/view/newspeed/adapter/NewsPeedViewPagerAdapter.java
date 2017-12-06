package com.hongsup.explog.view.newspeed.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.hongsup.explog.R;
import com.hongsup.explog.view.newspeed.view.NewsPeedItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class NewsPeedViewPagerAdapter extends PagerAdapter{

    private static final int COUNT = 5;
    private Context context;
    private List<View> views;

    public NewsPeedViewPagerAdapter(Context context) {
        this.context = context;
        views = new ArrayList<>(COUNT);
        views.add(new NewsPeedItemView(context));
        views.add(new NewsPeedItemView(context));
        views.add(new NewsPeedItemView(context));
        views.add(new NewsPeedItemView(context));
        views.add(new NewsPeedItemView(context));
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
        container.removeView((View)object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getResources().getString(R.string.asia);
            case 1:
                return context.getResources().getString(R.string.europe);
            case 2:
                return context.getResources().getString(R.string.americas);
            case 3:
                return context.getResources().getString(R.string.oceania);
            case 4:
                return context.getResources().getString(R.string.africa);
            default:
                return "띠용";
        }
    }

    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

}