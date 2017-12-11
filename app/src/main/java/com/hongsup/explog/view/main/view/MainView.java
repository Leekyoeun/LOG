package com.hongsup.explog.view.main.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.hongsup.explog.R;
import com.hongsup.explog.view.main.contract.MainContract;
import com.hongsup.explog.view.newspeed.view.NewsPeedView;
import com.hongsup.explog.view.cover.CoverActivity;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class MainView implements MainContract.iView, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

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
        navigation.setOnNavigationItemSelectedListener(this);
        disableShiftMode(navigation);
        frameLayout.addView(new NewsPeedView(context));
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setPresenter(MainContract.iPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        frameLayout.removeAllViews();
        switch (id) {
            case R.id.navigation_newspeed:
                // View 가 이미 있는지 체크
                frameLayout.addView(new NewsPeedView(context));
                return true;
            case R.id.navigation_search:
                return true;
            case R.id.navigation_post:
                // View 가 이미 있는지 체크
                Intent intent = new Intent(context, CoverActivity.class);
                context.startActivity(intent);
                return true;
            case R.id.navigation_notification:
                return true;
            case R.id.navigation_profile:
                return true;
        }
        return false;
    }

    /**
     * Bottom NavigationView Animation 제거
     *
     * @param view
     */
    @SuppressLint("RestrictedApi")
    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            //Timber.e(e, "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            //Timber.e(e, "Unable to change value of shift mode");
        }
    }

}
