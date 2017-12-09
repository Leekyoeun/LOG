package com.hongsup.explog.view.myinfo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


/**
 * Created by 정인섭 on 2017-12-07.
 */

public class CustomBehavior extends CoordinatorLayout.Behavior<LinearLayout>{
    final static String TAG = "hahahahohoho";

    public CustomBehavior() {
    }

    public CustomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "onDependentViewChanged11: ");
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, LinearLayout child, View dependency) {
        Log.d(TAG, "onDependentViewChanged22: ");
        Log.d(TAG, dependency.getY() + "");

        if(dependency.getY()>281){
            child.setVisibility(View.INVISIBLE);
        }else{
            child.setVisibility(View.VISIBLE);
        }
        return dependency instanceof android.support.v7.widget.Toolbar;
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, LinearLayout child, View dependency) {

        int[] dependencyLocation = new int[2];
        int[] childLocation = new int[2];

        dependency.getLocationInWindow(dependencyLocation);

        Log.d(TAG, "onDependentViewChanged33: ");
        Log.d("====Location", dependencyLocation[0] + ", " + dependencyLocation[1]);
        return false;
    }


}
