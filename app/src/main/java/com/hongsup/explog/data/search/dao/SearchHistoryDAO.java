package com.hongsup.explog.data.search.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hongsup.explog.util.DBHelperUtil;

import java.util.ArrayList;

/**
 * Created by 정인섭 on 2017-12-08.
 */

public class SearchHistoryDAO {

    DBHelperUtil helper;
    public SearchHistoryDAO(Context context){
        helper = new DBHelperUtil(context);
    }

    public void readQuery(String query){
        SQLiteDatabase connection = helper.getReadableDatabase();

        connection.execSQL(query);

        connection.close();
        close();
    }

    public ArrayList<String> read(){
        String query = "select id, word from history";
        ArrayList<String> list = new ArrayList<>();

        SQLiteDatabase connection = helper.getReadableDatabase();

        Cursor cursor = connection.rawQuery(query, null);
        while(cursor.moveToNext()){
            String word = cursor.getString(1);
            list.add(0, word);
        }
        cursor.close();
        connection.close();
        close();

        return list;
    }

    public void update(String query){

    }

    public void delete(String query){

    }

    public void close(){

        helper.close();

    }
}