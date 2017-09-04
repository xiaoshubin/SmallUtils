package com.smallcake.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    /**
     * 时间戳 转 String类型的精确到时分秒
     * @param time
     * @return
     */
    public static String tsToMs(int time) {
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        long time1000 = Long.parseLong(String.valueOf(time)) * 1000;
        return fm.format(time1000);
    }
    /**
     * 时间戳 转 String类型的年月日
     * @param time
     * @return
     */
    public static String tsToYMD(int time) {
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        long time1000 = Long.parseLong(String.valueOf(time)) * 1000;
        return fm.format(time1000);
    }
    /**
     * 获取今天年月日
     * @return 2017-08-14
     */
    public static String getTodayDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(date);
    }

    /**
     *  当前毫秒级时间
     * @return 2017/04/07-11:01:06:109
     */
    public static String getMillisecondTime() {
        return new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS", Locale.CHINA).format(new Date());
    }
    /**
     * 获取当前系统的时间戳
     * @return 1502697135
     */
    public static int getTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
