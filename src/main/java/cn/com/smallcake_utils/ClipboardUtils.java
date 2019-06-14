package cn.com.smallcake_utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.text.TextUtils;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * MyApplication --  cn.com.smallcake_utils
 * Created by Small Cake on  2018/3/20 14:21.
 * 剪贴板工具类
 */

public class ClipboardUtils {

    public static void copy( String msg) {
        if (TextUtils.isEmpty(msg)) {
            ToastUtil.showLong("复制的内容为空！");
            return;
        }
        ClipboardManager cm = (ClipboardManager) SmallUtils.getApp().getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(null, msg);
        cm.setPrimaryClip(clipData);
        ToastUtil.showLong("已复制");
    }

    public static String paste() {
        ClipboardManager cm = (ClipboardManager) SmallUtils.getApp().getSystemService(CLIPBOARD_SERVICE);
        ClipData cd2 = cm.getPrimaryClip();
        if (cd2 == null || cd2.getItemAt(0) == null) {
            ToastUtil.showLong("你的剪贴板内容为空，请先复制相关内容！");
            return null;
        }
        String s = null;
        try {
            s = cd2.getItemAt(0).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
