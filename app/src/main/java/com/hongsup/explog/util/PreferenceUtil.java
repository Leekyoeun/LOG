package com.hongsup.explog.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class PreferenceUtil {

    private static final String filename = "explog";

    private static SharedPreferences getPreference(Context context){
        return context.getSharedPreferences(filename, Context.MODE_PRIVATE);
    }

    public static void setValue(Context context, String key, String value){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static void setValue(Context context, String key, long value){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putLong(key, value);
        //editor.remove(key); 삭제하기
        editor.commit();
    }

    public static String getString(Context context, String key){
        return getPreference(context).getString(key,"");
    }

    public static Long getLong(Context context, String key){
        return getPreference(context).getLong(key,0);
    }

    public static void removeValue(Context context, String key){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.remove(key);
        editor.commit();
    }

    public static void removeAllValue(Context context){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.clear();
        editor.commit();
    }


}
