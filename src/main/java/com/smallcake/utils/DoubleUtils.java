package com.smallcake.utils;

import java.math.BigDecimal;

/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2018/3/17 15:04.
 */

public class DoubleUtils {
    /**
     * 两个float的高精度算法
     * @param d1
     * @param d2
     * @return
     */
    public static double multiply(double d1,double d2){
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.multiply(b2).doubleValue();
    }
}
