package com.hongsup.explog.view.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by Android Hong on 2017-12-09.
 */

@SuppressLint("AppCompatCustomView")
public class BackPressEditText extends EditText {
    public BackPressEditText(Context context) {
        super(context);
    }

    public BackPressEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BackPressEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onKeyPreIme(int key_code, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP)
            this.clearFocus();

        return super.onKeyPreIme(key_code, event);
    }
}