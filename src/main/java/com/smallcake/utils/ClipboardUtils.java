package com.smallcake.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2018/3/20 14:21.
 * 剪贴板工具类
 */

public class ClipboardUtils {

    public static void copy(Context context, String msg) {
        if (TextUtils.isEmpty(msg)) {
            T.showLong(context.getString(R.string.data_is_null));
            return;
        }
        ClipboardManager cm = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(null, msg);
        cm.setPrimaryClip(clipData);
        T.showLong(context.getString(R.string.copy_success));
    }

    public static String paste(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData cd2 = cm.getPrimaryClip();
        if (cd2 == null || cd2.getItemAt(0) == null) {
            
            T.showLong(context.getString(R.string.your_clipboard_is_null));
            return null;
        }
        String s = cd2.getItemAt(0).getText().toString();
        return s;
    }
}
