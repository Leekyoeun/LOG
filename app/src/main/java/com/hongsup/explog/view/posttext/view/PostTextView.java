package com.hongsup.explog.view.posttext.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.hongsup.explog.R;
import com.hongsup.explog.data.Const;
import com.hongsup.explog.data.post.Content;
import com.hongsup.explog.view.post.PostActivity;
import com.hongsup.explog.view.posttext.contract.PostTextContract;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hong on 2017-12-14.
 */

public class PostTextView implements PostTextContract.iView {

    private Context context;
    private PostTextContract.iPresenter presenter;
    private View view;
    private Intent postIntent;
    private Intent uploadIntent;
    private Menu menu;
    private Content content;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editText)
    EditText editText;

    public PostTextView(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.activity_post_text, null);
        ButterKnife.bind(this, view);

        /*
         Upload 할 Intent 설정
         */
        uploadIntent = new Intent(context, PostActivity.class);
        postIntent = ((Activity) context).getIntent();
        content = (Content) postIntent.getSerializableExtra(Const.INTENT_EXTRA_TEXT);

        initToolbar();
        setContentData();
    }

    @Override
    public void setPresenter(PostTextContract.iPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setMenu(Menu menu) {
        this.menu = menu;
        ((Activity) context).getMenuInflater().inflate(R.menu.menu_cover, menu);

        RxTextView.textChangeEvents(editText)
                .subscribe(ch -> {
                    if (ch.text().length() > 0) {
                        changeMenu(R.id.action_ok, true);
                    } else {
                        changeMenu(R.id.action_ok, false);
                    }
                });
    }

    @Override
    public void onMenuClick(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_ok:
                if (content != null) {
                    // 글 수정인 경우

                } else {
                    // 글 작성인 경우
                    uploadIntent.putExtra(Const.INTENT_EXTRA_TEXT, editText.getText().toString());
                    uploadIntent.putExtra(Const.INTENT_EXTRA_DATE, "2017-12-15");

                    ((Activity) context).setResult(Activity.RESULT_OK, uploadIntent);
                    ((Activity) context).finish();
                }
                break;
        }
    }

    private void initToolbar() {
        ((AppCompatActivity) context).setSupportActionBar(toolbar);
        ((AppCompatActivity) context).getSupportActionBar().setTitle(context.getResources().getString(R.string.post_content_text));
        ((AppCompatActivity) context).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });
    }

    private void setContentData() {
        if (content != null) {
            editText.setText(content.getContent());
        }
    }

    /**
     * editText 변화에 대한 Menu 변환
     *
     * @param id    Menu Resource ID
     * @param check flag
     */
    private void changeMenu(int id, boolean check) {
        MenuItem item = menu.findItem(id);
        if (check) {
            item.setIcon(R.drawable.ic_check_white_24dp);
            item.setIntent(uploadIntent);
            item.setEnabled(true);
        } else {
            item.setIcon(R.drawable.ic_check_gray_24dp);
            item.setIntent(null);
            item.setEnabled(false);
        }
    }


}
