package com.hongsup.explog.view.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hongsup.explog.R;
import com.hongsup.explog.view.search.dao.HistoryDAO;

import java.util.ArrayList;

/**
 * Created by 정인섭 on 2017-12-08.
 */

public class SearchView extends FrameLayout implements SearchRecyclerAdapter.ListAction {
    EditText editSearch;
    RecyclerView recyclerView;
    SearchRecyclerAdapter searchRecyclerAdapter;
    HistoryDAO dao;
    ImageView imgDeleteTextSearch;
    public SearchView(@NonNull Context context) {
        super(context);
        init();
        setRecyclerView();
        readList();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_search, null);
        editSearch = view.findViewById(R.id.editSearch);
        recyclerView = view.findViewById(R.id.recyclerSearchHistory);
        imgDeleteTextSearch = view.findViewById(R.id.imgDeleteTextSearch);

        dao = new HistoryDAO(getContext());
        setListener();

        addView(view);
    }

    private void insert(){
        String word = editSearch.getText().toString();
        HistoryDAO dao = new HistoryDAO(getContext());
        String deleteQuery = "delete from history where word = '" + word + "'";
        String insertquery = "insert into history(word)" + " values('" + word + "')";
        dao.readQuery(deleteQuery);
        dao.readQuery(insertquery);
        readList();
        Log.d("SearchView", "readQuery() 작동");
    }

    private void setListener(){
        editSearch.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(!"".equals(editSearch.getText().toString()) && keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()== KeyEvent.ACTION_UP){
                    insert();
                    editSearch.setText("");
                }else if("".equals(editSearch.getText().toString()) && keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()== KeyEvent.ACTION_UP){
                    Log.d("작동작동", "작동");
                    //editSearch.setSelection(editSearch.length());
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
                if(!"".equals(editSearch.getText().toString())){
                    editSearch.setText("");
                }
            }
        });
    }

    private void setRecyclerView(){
        searchRecyclerAdapter = new SearchRecyclerAdapter(this);
        recyclerView.setAdapter(searchRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void readList(){
        searchRecyclerAdapter.notifier(dao.read());
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
