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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hongsup.explog.R;
import com.hongsup.explog.data.post.PostCover;
import com.hongsup.explog.data.search.dao.SearchHistoryDAO;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.api.SearchAPI;
import com.hongsup.explog.view.search.insuptest.SearchResponse;
import com.hongsup.explog.view.search.insuptest.Word;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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
    @BindView(R.id.progressBar3)
    ProgressBar progressBar;
    @BindView(R.id.textSearching)
    TextView textSearching;
    @BindView(R.id.relativeSearching)
    RelativeLayout relativeSearching;

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
        word = new Word();
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


        RxTextView.textChanges(editSearch)
                .subscribe(ch -> {
                    if (ch.length() > 0) {
                        String com = "'" + ch.toString() + "' 검색중입니다";
                        textSearching.setText(com);
                        relativeSearching.setVisibility(VISIBLE);
                        recyclerSearchHistory.setAdapter(searchRecyclerResultAdapter);
                        word.setWord(ch.toString());
                        getDataTwo(word);
                        relativeSearching.setVisibility(INVISIBLE);
                        //searchRecyclerResultAdapter.resultNotifier(list);

                    } else {

                        recyclerSearchHistory.setAdapter(searchRecyclerAdapter);

                    }
                });
    }


    private void setRecyclerView() {
        searchRecyclerAdapter = new SearchRecyclerAdapter(this);
        searchRecyclerResultAdapter = new SearchRecyclerResultAdapter();
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

    ArrayList<PostCover> list = new ArrayList<>();

    public void getDataTwo(Word word) {
        progressBar.setVisibility(VISIBLE);
        SearchAPI searchAPI = ServiceGenerator.create(SearchAPI.class);
        Observable<Response<ArrayList<PostCover>>> observable = searchAPI.observable(word);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {

                    if (data.isSuccessful()) {
                        if (data.code() == 200) {
                            Log.d("SearchView", "확인됨");
                            list = data.body();
                            searchRecyclerResultAdapter.resultNotifier(list);
                            Log.d("SearchView", "데이터 없음" + data.body().size());
//                            Log.d("SearchView", "데이터 없음" + data.errorBody());
//                            Log.d("SearchView", "데이터 없음" + data.message());
//                            Log.d("SearchView", "데이터 없음" + data.raw());
//                            Log.d("SearchView", "데이터 없음" + data.headers());
//                            Log.d("SearchView", "데이터 없음" + data.code());
//                            if(data.body().size()==0){
//                                Log.d("SearchView", "뜬다 떠");
//                            }

                        } else {
                            Log.d("SearchView", "data is not Successful");
                        }
                    } else {
                        Log.e("SearchView", " Error");
                    }
                }, throwable -> {
                    Log.e("SearchView", throwable.getMessage());
                });
        progressBar.setVisibility(INVISIBLE);
    }
}
