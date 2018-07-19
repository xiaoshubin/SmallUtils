package cn.com.smallcake_utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

/**
 * MyApplication --  cn.com.smallcake_utils
 * Created by Small Cake on  2017/8/24 11:33.
 */

public class ActivityUtils {
    /**
     * 获取当前Activity的根节点
     * @param context
     * @return
     */
    public static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }
}
