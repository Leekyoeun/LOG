package com.hongsup.explog.view.cover;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.photo.Photo;
import com.hongsup.explog.util.DateUtil;
import com.hongsup.explog.util.DialogUtil;
import com.hongsup.explog.view.custom.LimitedEditText;
import com.hongsup.explog.view.gallery.GalleryActivity;
import com.hongsup.explog.view.post.PostActivity;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;

import static com.hongsup.explog.data.Const.REQ_GALLERY;

public class CoverActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "PostActivity";

    boolean endFlag;

    LimitedEditText editTitle;
    ScrollView scrollView;
    RelativeLayout relativeLayout;
    TextView textCount, textStartDate, textEndDate;
    DatePickerDialog dialog;
    Button btnChangeCover;
    ImageView imgCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);
        initToolbar();
        initView();
        initListener();
        setTextCount();
        setStartDate();
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
        btnChangeCover = findViewById(R.id.btnChangeCover);
        imgCover = findViewById(R.id.imgCover);

    }

    private void setTextCount() {
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

    private void setStartDate() {
        textStartDate.setText(DateUtil.currentDate());
    }

    private void initListener() {
        textStartDate.setOnClickListener(this);
        textEndDate.setOnClickListener(this);
        btnChangeCover.setOnClickListener(this);

        editTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                /**
                 * focus 있을 경우
                 * ScrollView 하단으로 스크롤하는 메소드
                 *
                 * 수정사항 )
                 *
                 * scollView 를 움직일 경우
                 * focus 의 문제로 인해?
                 * 뒤로가기를 눌렀을 시에도 focus 가 있는 경우가 있다.
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
        getMenuInflater().inflate(R.menu.menu_cover, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_ok:
                Intent intent = new Intent(CoverActivity.this, PostActivity.class);
                startActivity(intent);

                // finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.textStartDate:
                dialog = DialogUtil.showDatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        textStartDate.setText(String.format("%d.%d.%d", year, month+1, day));
                    }
                },textStartDate.getText().toString());

                if(endFlag){
                    /**
                     *  최대 날짜 설정
                     */
                    dialog.getDatePicker().setMaxDate(DateUtil.getConvertDate(textEndDate.getText().toString()));
                }

                dialog.show();
                break;
            case R.id.textEndDate:
                dialog  = DialogUtil.showDatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        endFlag = true;
                        textEndDate.setText(String.format("%d.%d.%d", year, month+1, day));
                    }
                },textEndDate.getText().toString());

                /**
                 * 최소 날짜 설정
                 */
                dialog.getDatePicker().setMinDate(DateUtil.getConvertDate(textStartDate.getText().toString()));
                dialog.show();

                break;
            case R.id.btnChangeCover:
                Intent intent = new Intent(this, GalleryActivity.class);
                startActivityForResult(intent, REQ_GALLERY);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case REQ_GALLERY :
                if(resultCode == RESULT_OK){
                    if(data != null) {
                        ArrayList<Photo> photoList = (ArrayList<Photo>) data.getSerializableExtra("PHOTO");

                        Glide.with(this)
                                .load(photoList.get(0).getImagePath())
                                .centerCrop()
                                .into(imgCover);
                        imgCover.setColorFilter(ContextCompat.getColor(this, R.color.colorPostTint), PorterDuff.Mode.SRC_OVER);

                    }
                }
        }
    }
}
