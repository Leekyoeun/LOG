package com.hongsup.explog.view.search;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.post.source.PostCoverList;
import com.hongsup.explog.data.search.dao.SearchHistoryDAO;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.api.SearchAPI;
import com.hongsup.explog.view.custom.PostItemDivider;
import com.hongsup.explog.view.search.insuptest.Word;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

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
    SearchRecyclerResultAdapter searchRecyclerResultAdapter;

    SearchHistoryDAO dao;
    Word word;
    @BindView(R.id.textSearching)
    TextView textSearching;
    @BindView(R.id.relativeSearching)
    RelativeLayout relativeSearching;
    @BindView(R.id.progressBar3)
    ProgressBar progressBar;

    InputMethodManager inputMethodManager;

    public SearchView(@NonNull Context context) {
        super(context);
        init(context);
        setRecyclerView();
        executeList();
    }

    private void init(@NonNull Context context) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_search, null);
        ButterKnife.bind(this, view);
        dao = new SearchHistoryDAO(getContext());
        setListener();
        addView(view);
        word = new Word();
        inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
    }

    // mysql에 검색어 넣는 과정
    private void insert() {
        String word = editSearch.getText().toString();
        SearchHistoryDAO dao = new SearchHistoryDAO(getContext());
        String deleteQuery = "delete from history where word = '" + word + "'";
        String insertquery = "insert into history(word)" + " values('" + word + "')";
        dao.readQuery(deleteQuery);
        dao.readQuery(insertquery);
        executeList();
        Log.d("SearchView", "readQuery() 작동");
    }

    private void setListener() {
        editSearch.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (!"".equals(editSearch.getText().toString())
                        && keyCode == KeyEvent.KEYCODE_ENTER
                        && event.getAction() == KeyEvent.ACTION_UP) {
                    insert();
                    // 키보드 사라짐
                    inputMethodManager.hideSoftInputFromWindow(editSearch.getWindowToken(), 0);
                    //editSearch.setText("");
                }
                return false;
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

        RxTextView.textChanges(editSearch)
                .subscribe(ch -> {
                    if (ch.length() > 0) {
                        relativeSearching.setVisibility(INVISIBLE);
                        recyclerSearchHistory.setAdapter(searchRecyclerResultAdapter);
                        word.setWord(ch.toString());
                        getDataTwo(word);
                        //결과가 검색이 되면 스크롤 움직였을 때 소프트 키보드 사라짐 12/20
                        recyclerSearchHistory.setOnTouchListener(new OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if(event.getAction()==MotionEvent.ACTION_MOVE){
                                    inputMethodManager.hideSoftInputFromWindow(recyclerSearchHistory.getWindowToken(), 0);
                                }
                                return false;
                            }
                        });

                        //searchRecyclerResultAdapter.resultNotifier(list);

                    } else {
                        relativeSearching.setVisibility(INVISIBLE);
                        recyclerSearchHistory.setAdapter(searchRecyclerAdapter);

                    }
                });
    }


    private void setRecyclerView() {
        searchRecyclerAdapter = new SearchRecyclerAdapter(this);
        searchRecyclerResultAdapter = new SearchRecyclerResultAdapter(getContext());
        recyclerSearchHistory.setAdapter(searchRecyclerAdapter);
        recyclerSearchHistory.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void executeList() {
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
        executeList();
    }

    ArrayList<PostCover> list = new ArrayList<>();


    public void getDataTwo(Word word) {
        progressBar.setVisibility(VISIBLE);
        SearchAPI searchAPI = ServiceGenerator.create(SearchAPI.class);
        Observable<Response<PostCoverList>> observable = searchAPI.observable(word);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {

                    if (data.isSuccessful()) {
                        if (data.code() == 200) {
                            Log.d("SearchView", "확인됨");

                            list = data.body().getPostCover();
                            searchRecyclerResultAdapter.resultNotifier(list);
                            if (list.size() == 0) {
                                relativeSearching.setVisibility(VISIBLE);
                            }

                            progressBar.setVisibility(INVISIBLE);
                        } else {
                            Log.d("SearchView", "data is not Successful");
                        }
                    } else {
                        Log.e("SearchView", " Error");
                    }
                }, throwable -> {
                    Log.e("SearchView", throwable.getMessage());
                });
    }
}