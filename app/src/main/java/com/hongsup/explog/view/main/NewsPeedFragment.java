package com.hongsup.explog.view.main;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongsup.explog.R;
import com.hongsup.explog.view.main.adapter.NewsPeedViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Hong on 2017-12-05.
 */

public class NewsPeedFragment extends Fragment {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    private View view;

    public NewsPeedFragment() {
        // Required empty public constructor
    }

    // Fragment 의 경우 onCreate 에서 View 의 작업을 하면 안되고
    // onCreateView 에서 View 의 작업을 해줘야 한다.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.view_newspeed, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        NewsPeedViewPagerAdapter newsPeedViewPagerAdapter = new NewsPeedViewPagerAdapter(getActivity());
        viewPager.setAdapter(newsPeedViewPagerAdapter);
    }


}

