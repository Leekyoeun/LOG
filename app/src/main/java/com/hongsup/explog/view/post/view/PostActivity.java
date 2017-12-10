package com.hongsup.explog.view.post.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hongsup.explog.R;
import com.hongsup.explog.view.custom.BackPressEditText;
import com.jakewharton.rxbinding2.widget.RxTextView;

public class PostActivity extends AppCompatActivity {

    private static final String TAG = "PostActivity";

    BackPressEditText editTitle;
    ScrollView scrollView;
    RelativeLayout relativeLayout;

    TextView textCount, textStartDate, textEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initToolbar();
        initView();
        initListener();
        setTextCount();
    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        /**
         * Toolbar Setting
         */
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        scrollView = findViewById(R.id.scrollView);
        editTitle = findViewById(R.id.editTitle);
        relativeLayout = findViewById(R.id.relativeLayout);
        textCount = findViewById(R.id.textCount);
        textStartDate = findViewById(R.id.textStartDate);
        textEndDate = findViewById(R.id.textEndDate);
    }

    private void setTextCount() {
        //textCount.setText(editTitle.getText().toString().length() + "/50");

        RxTextView.textChangeEvents(editTitle)
                .subscribe(ch -> {
                    if (ch.text().length() > 0) {
                        textCount.setVisibility(View.VISIBLE);
                        textCount.setText(ch.text().length() + "/50");
                    } else {
                        textCount.setVisibility(View.GONE);
                    }
                });
    }

    private void initListener() {
        editTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                /**
                 * focus 있을 경우
                 * ScrollView 하단으로 스크롤하는 메소드
                 */
                if (hasFocus) {
                    scrollView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.smoothScrollBy(0, scrollView.getHeight());
                            editTitle.requestFocusFromTouch();
                        }
                    }, 280);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_ok:
                Toast.makeText(this, "클릭", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
