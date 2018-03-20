package com.smallcake.utils;

import android.content.Context;
import android.text.format.Formatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2017/8/15 14:05.
 */

public class FormatUtils {
    /**
     * trans KB, MB, GB
     *
     * @param sizeBytes long
     * @return 47kb, 4.70M, 1.47GB
     */
    public static String formatSize(Context context, long sizeBytes) {
        return Formatter.formatFileSize(context, sizeBytes);
    }

    /**
     * trans 47%
     *
     * @param currentLength
     * @param totalLength
     * @return 47
     */
    public static int getProgress(long currentLength, long totalLength) {
        return (int) ((currentLength * 100) / totalLength);
    }

    /**
     * 1,000.00
     *
     * @param num
     * @return
     */
    public static String qianweifenge(float num) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String ss = df.format(num);
        return ss;
    }

    public static String twoDecimal(float f) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        return nf.format(f);
    }
}
