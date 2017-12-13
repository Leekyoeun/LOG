package com.hongsup.explog.view.setting;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.hongsup.explog.R;

/**
 * Created by 정인섭 on 2017-12-11.
 */

public class EditProfileView extends RelativeLayout {

    public EditProfileView(@NonNull Context context) {
        super(context);
        init();

    }

    public void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_setting_edit_profile, this, false);
        setBackgroundColor(Color.WHITE);
        addView(view);
    }


}
