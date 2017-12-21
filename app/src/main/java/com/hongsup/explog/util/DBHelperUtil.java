package com.hongsup.explog.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 정인섭 on 2017-12-08.
 *  -
 * Modified by Android Hong on 2017-12-20
 *  - Singleton 적용 완료
 */

public class DBHelperUtil extends SQLiteOpenHelper {

    private static final String DB_NAME = "explog.db";
    private static final int DB_VERSION = 1;

    private static DBHelperUtil instance = null;

    public static DBHelperUtil getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelperUtil(context);
        }
        return instance;
    }

    private DBHelperUtil(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE 'history' (" +
                " 'id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " 'word' TEXT " +
                " )";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
