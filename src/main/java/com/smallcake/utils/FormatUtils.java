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
        NumberFormat nf = NumberFormat.getNumberInstance();//建立数字格式化引用
        nf.setMaximumFractionDigits(2); //百分比小数点最多2位
        return nf.format(f);
    }
    public void test() {
        DecimalFormat df = new DecimalFormat();
        double data = 1234.56789; //格式化之前的数字
        //1、定义要显示的数字的格式（这种方式会四舍五入）
        String style = "0.0";
        df.applyPattern(style);
        System.out.println("1-->" + df.format(data));  //1234.6

        //2、在格式后添加诸如单位等字符
        style = "00000.000 kg";
        df.applyPattern(style);
        System.out.println("2-->" + df.format(data));  //01234.568 kg


        //3、 模式中的"#"表示如果该位存在字符，则显示字符，如果不存在，则不显示。
        style = "##000.000 kg";
        df.applyPattern(style);
        System.out.println("3-->" + df.format(data));  //1234.568 kg

        //4、 模式中的"-"表示输出为负数，要放在最前面
        style = "-000.000";
        df.applyPattern(style);
        System.out.println("4-->" + df.format(data)); //-1234.568


        //5、 模式中的","在数字中添加逗号，方便读数字
        style = "-0,000.0#";
        df.applyPattern(style);
        System.out.println("5-->" + df.format(data));  //5-->-1,234.57


        //6、模式中的"E"表示输出为指数，"E"之前的字符串是底数的格式，
        // "E"之后的是字符串是指数的格式
        style = "0.00E000";
        df.applyPattern(style);
        System.out.println("6-->" + df.format(data));  //6-->1.23E003


        //7、 模式中的"%"表示乘以100并显示为百分数，要放在最后。
        style = "0.00%";
        df.applyPattern(style);
        System.out.println("7-->" + df.format(data));  //7-->123456.79%


        //8、 模式中的"\u2030"表示乘以1000并显示为千分数，要放在最后。
        style = "0.00\u2030";
        //在构造函数中设置数字格式
        DecimalFormat df1 = new DecimalFormat(style);
        //df.applyPattern(style);
        System.out.println("8-->" + df1.format(data));  //8-->1234567.89‰
    }
}
