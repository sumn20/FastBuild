package com.seeyou.toolkit.utils;

import android.widget.Toast;

import androidx.annotation.StringRes;


/**
 * Author:Sumn
 * Email:
 * CreateTime: 2018/7/27
 * Description：
 */

public class ToastUtil {
    private ToastUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static Toast toast = null;

    public static void showLong(String msg) {
        Toast.makeText(Utils.getApp(), msg, Toast.LENGTH_LONG).show();
    }

    public static void showShort(String msg) {
        Toast.makeText(Utils.getApp(), msg, Toast.LENGTH_SHORT).show();
    }


    public static void showLong(@StringRes int msg) {
        Toast.makeText(Utils.getApp(), Utils.getApp().getString(msg), Toast.LENGTH_LONG).show();
    }

    public static void showShort(@StringRes int msg) {

        Toast.makeText(Utils.getApp(), Utils.getApp().getString(msg), Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("all")
    public static void showOneLong(String msg) {
        if (toast == null) {
            toast = Toast.makeText(Utils.getApp(), msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    @SuppressWarnings("all")
    public static void showOneShort(String msg) {
        if (toast == null) {
            toast = Toast.makeText(Utils.getApp(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
