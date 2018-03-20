package com.smallcake.utils;

import java.util.Random;

/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2018/2/9 11:07.
 */

public class RadomUtils {
    public static float getFloat(float min,float max) {
        return min + new Random().nextFloat() * (max - min);
    }
}
