package cn.com.smallcake_utils;

import java.math.BigDecimal;

/**
 * MyApplication --  cn.com.smallcake_utils
 * Created by Small Cake on  2018/3/17 15:04.
 */

public class DoubleUtils {
    /**
     * 加
     * @param d1
     * @param d2
     * @return
     */
    public static double add(double d1,double d2){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 减
     * @param d1
     * @param d2
     * @return
     */
    public static double sub(double d1,double d2){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.subtract(b2).doubleValue();
    }
    /**
     * 乘
     * @param d1
     * @param d2
     * @return
     */
    public static double multiply(double d1,double d2){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 除
     * @param d1
     * @param d2
     * @return
     */
    public static double div(double d1,double d2){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2).doubleValue();
    }


}
