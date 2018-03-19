package com.hongsup.explog.view.setting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.hongsup.explog.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity implements SettingRecyclerAdapter.ISettingView {

//    @BindView(R.id.settingToolbar)
    Toolbar settingToolbar;
    RecyclerView settingRecyclerView;
    SettingRecyclerAdapter settingRecyclerAdapter;
    @BindView(R.id.settingRelativeLayout)
    RelativeLayout settingRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setRecyclerView();
        //setToolbar();

    }

    public void reflection(){

        try {
            Class<?> cls = Class.forName("com.hongsup.explog.view.myinfo.view.MyInfoLayout");
            Class[] constructorParamClass = new Class[] {Context.class};
            Object[] constructorParamObject = new Object[] {this};
            Constructor constructor = cls.getConstructor(constructorParamClass);
            Object objectClass = constructor.newInstance(constructorParamObject);

            Method method = cls.getDeclaredMethod("getDataFromDB");
            method.invoke(objectClass);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                reflection();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public void setToolbar(){
//        settingToolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
//    }

    public void setRecyclerView(){
        settingRecyclerView = findViewById(R.id.settingRecyclerView);
        settingRecyclerAdapter = new SettingRecyclerAdapter(this);
        settingRecyclerView.setAdapter(settingRecyclerAdapter);
        settingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(resultCode){
            case RESULT_OK :
                logOut(data.getBooleanExtra("alarm", true));
        }
    }


    @Override
    public void logOut(boolean isChecked) {

    }
}
