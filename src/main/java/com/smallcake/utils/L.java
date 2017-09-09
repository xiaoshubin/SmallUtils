package com.smallcake.utils;

/**
 * MyApplication --  com.cake.util
 * Created by Small Cake on  2016/4/26 15:50.
 * <p>
 * default is true
 * L.setIsDebug(BuildConfig.DEBUG);into your MyApplication
 * if you don't want watch Log print by others
 */

import android.util.Log;

public class L {

    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // need print deafult true
    public static boolean isDebug = true;
    private static final String TAG = ">>>>>";
    private static int LOG_MAXLENGTH = 2000;


    public static void i(String msg) {
        if (isDebug) Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug) Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug) Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug) Log.v(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (isDebug) Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug) Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug) Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug) Log.v(tag, msg);
    }

    /**
     * if your logmessage too much,you can take this
     * @param msg
     */
    public static void iAll(String msg) {
        if (!isDebug) return;

        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAXLENGTH;

        for (int i = 0; i < 47; i++) {
            if (strLength > end) {
                Log.i(TAG + i, msg.substring(start, end));
                start = end;
                end = end + LOG_MAXLENGTH;
            } else {
                Log.i(TAG + i, msg.substring(start, strLength));
                break;
            }
        }


    }

    /**
     default is true
     if you don't want watch Log print by others take under code
     L.setIsDebug(BuildConfig.DEBUG);
     into your MyApplication
     */
    public static void setIsDebug(boolean isDebug) {
        L.isDebug = isDebug;
    }
}
