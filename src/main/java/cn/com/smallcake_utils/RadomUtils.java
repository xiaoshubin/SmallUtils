package cn.com.smallcake_utils;

import java.util.Random;

/**
 * MyApplication --  cn.com.smallcake_utils
 * Created by Small Cake on  2018/2/9 11:07.
 */

public class RadomUtils {
    public static float getFloat(float min,float max) {
        return min + new Random().nextFloat() * (max - min);
    }
}
