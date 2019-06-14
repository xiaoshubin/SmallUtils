package cn.com.smallcake_utils;

import android.content.Context;
import android.text.format.Formatter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * MyApplication --  cn.com.smallcake_utils
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



    public static String twoDecimal(float f) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        return nf.format(f);
    }

    /**
     * 必须保留两位小数
     * @param f
     * @return
     */
    public static String twoDecimalMast(float f) {
        DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
        String ss = df.format(f);
        return ss;
    }
    public static String numberFormat(BigDecimal num, int decimalDigits) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(decimalDigits);
        return nf.format(num);
    }
    public static String eightDecimalMast(BigDecimal num) {
        return numberFormat(num,8);
    }

}
