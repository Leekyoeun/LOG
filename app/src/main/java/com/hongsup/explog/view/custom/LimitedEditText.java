package com.hongsup.explog.view.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Android Hong on 2017-12-10.
 */

@SuppressLint("AppCompatCustomView")
public class LimitedEditText extends EditText {

    /**
     * Max lines to be present in editable text field
     */
    private int maxLines = 2;

    /**
     * Max characters to be present in editable text field
     */
    private int maxCharacters = 50;

    /**
     * application context;
     */
    private Context context;

    public int getMaxCharacters() {
        return maxCharacters;
    }

    public void setMaxCharacters(int maxCharacters) {
        this.maxCharacters = maxCharacters;
    }

    @Override
    public int getMaxLines() {
        return maxLines;
    }

    @Override
    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
    }

    public LimitedEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        this.setBackground(null);
    }

    public LimitedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setBackground(null);
    }

    public LimitedEditText(Context context) {
        super(context);
        this.context = context;
        this.setBackground(null);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        TextWatcher watcher = new TextWatcher() {

            private String text;
            private int beforeCursorPosition = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //TODO sth
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                text = s.toString();
                beforeCursorPosition = start;
            }

            @Override
            public void afterTextChanged(Editable s) {

            /* turning off listener */
                removeTextChangedListener(this);

            /* handling lines limit exceed */
                if (LimitedEditText.this.getLineCount() > maxLines) {
                    LimitedEditText.this.setText(text);
                    LimitedEditText.this.setSelection(beforeCursorPosition);
                }

            /* handling character limit exceed */
                if (s.toString().length() > maxCharacters) {
                    LimitedEditText.this.setText(text);
                    LimitedEditText.this.setSelection(beforeCursorPosition);
                    Toast.makeText(context, "text too long", Toast.LENGTH_SHORT)
                            .show();
                }

            /* turning on listener */
                addTextChangedListener(this);

            }
        };

        this.addTextChangedListener(watcher);
    }

    @Override
    public boolean onKeyPreIme(int key_code, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_UP)
            this.clearFocus();

        return super.onKeyPreIme(key_code, event);
    }
}