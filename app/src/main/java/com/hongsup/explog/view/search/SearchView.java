package com.hongsup.explog.view.search;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hongsup.explog.R;
import com.hongsup.explog.data.search.dao.SearchHistoryDAO;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

/**
 * Created by 정인섭 on 2017-12-08.
 */

public class SearchView extends FrameLayout implements SearchRecyclerAdapter.ListAction {
    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.recyclerSearchHistory)
    RecyclerView recyclerSearchHistory;
    @BindView(R.id.imgDeleteTextSearch)
    ImageView imgDeleteTextSearch;
    SearchRecyclerAdapter searchRecyclerAdapter;
    SearchHistoryDAO dao;

    public SearchView(@NonNull Context context) {
        super(context);
        init(context);
        setRecyclerView();
        readList();


    }

    private void init(Context context) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_search, null);
        ButterKnife.bind(this, view);
        dao = new SearchHistoryDAO(getContext());
        setListener();
        addView(view);
        ((Activity)context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    private void insert() {
        String word = editSearch.getText().toString();
        SearchHistoryDAO dao = new SearchHistoryDAO(getContext());
        String deleteQuery = "delete from history where word = '" + word + "'";
        String insertquery = "insert into history(word)" + " values('" + word + "')";
        dao.readQuery(deleteQuery);
        dao.readQuery(insertquery);
        readList();
        Log.d("SearchView", "readQuery() 작동");
    }

    private void setListener() {
        editSearch.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (!"".equals(editSearch.getText().toString()) && keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    insert();
                    editSearch.setText("");
                }
                return false;
            }
        });

        editSearch.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        imgDeleteTextSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(editSearch.getText().toString())) {
                    editSearch.setText("");
                }
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if("".equals(editSearch.getText().toString())){
                    Log.d("확인", "onTextChanged: ");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        editSearch.addTextChangedListener(textWatcher);



    }
    @OnTextChanged(value = R.id.editSearch, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void textChangeListener () {


    }

    private void setRecyclerView() {
        searchRecyclerAdapter = new SearchRecyclerAdapter(this);
        recyclerSearchHistory.setAdapter(searchRecyclerAdapter);
        recyclerSearchHistory.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void readList() {
        searchRecyclerAdapter.historyNotifier(dao.read());
    }

    @Override
    public void setHistoryToSearchEditText(String word) {
        editSearch.setText(word);
        editSearch.setSelection(word.length());
    }

    @Override
    public void deleteHistory(String word) {
        String deleteQuery = "delete from history where word = '" + word + "'";
        dao.readQuery(deleteQuery);
        readList();
    }
}
