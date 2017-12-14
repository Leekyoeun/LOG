package com.hongsup.explog.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.hongsup.explog.R;

import java.util.Calendar;

/**
 * Created by Hong on 2017-12-10.
 */

public class DialogUtil {

    private static Calendar cal = Calendar.getInstance();

    /**
     * Date 를 선택하는 Dialog 호출
     *
     * @param context
     * @param listener
     * @param date
     * @return
     */
    public static DatePickerDialog showDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener, String date) {
        DatePickerDialog dialog;
        String[] str_date = date.split("\\.");

        if (str_date.length == 1) {
            dialog = new DatePickerDialog((Activity) context,
                    listener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH));

        } else {
            dialog = new DatePickerDialog((Activity) context,
                    listener,
                    Integer.parseInt(str_date[0]),
                    Integer.parseInt(str_date[1]) - 1,
                    Integer.parseInt(str_date[2]));
        }

        return dialog;
    }

    /**
     * Alert 를 선택한는 다이얼로그
     *
     * @param context
     * @param listener
     * @return
     */
    public static AlertDialog showAlertDialog(Context context, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        /*
         setItems( 아이템의 목록, 클릭했을 경우에 대한 리스너 )
         */
        builder.setItems(R.array.area, listener);
        return builder.create();
    }

    /**
     * 대륙 선택하는 다이얼로그
     *
     * @param context
     * @param listener
     * @return
     */
    public static AlertDialog showAreaDialog(Context context, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.choose_area);
        builder.setItems(R.array.area, listener);
        return builder.create();
    }
}
