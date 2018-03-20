package com.smallcake.utils;

import java.math.BigDecimal;

/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2018/3/17 15:04.
 */

public class FloatUtils {
    /**
     * 两个float的高精度算法
     * @param f1
     * @param f2
     * @return
     */
    public static float multiply(float f1,float f2){
        BigDecimal b1 = new BigDecimal(Float.toString(f1));
        BigDecimal b2 = new BigDecimal(Float.toString(f2));
        return b1.multiply(b2).floatValue();
    }
}
