package com.smallcake.utils;

import android.content.Context;
import android.text.format.Formatter;

/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2017/8/15 14:05.
 */

public class FormatUtils {
    /**
     * 工具类 根据文件大小自动转化为KB, MB, GB
     * @param sizeBytes long
     * @return
     */
    public static String formatSize(Context context,long sizeBytes) {
        return Formatter.formatFileSize(context, sizeBytes);
    }
}
