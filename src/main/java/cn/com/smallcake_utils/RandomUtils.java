package cn.com.smallcake_utils;

import java.util.Random;

/**
 * Project20180408 --  cn.com.smallcake_utils
 * Created by Small Cake on  2018/4/26 17:49.
 */
public class RandomUtils {

    public static int getIntRandom(int num){
         return new Random().nextInt(num);
    }
}
