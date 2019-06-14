package cn.com.smallcake_utils;

import java.math.BigInteger;

/**
 * Project20180408 --  cn.com.smallcake_utils
 * Created by Small Cake on  2018/6/13 16:04.
 * 进制转换工具
 */
public class HexadecimalUtils {

    public static String Hex16To10(String hex16){
        String cleanInput = cleanHexPrefix(hex16);
        BigInteger bigInteger = new BigInteger(cleanInput,16);
        return bigInteger.toString();
    }

    public static String cleanHexPrefix(String input) {
        if (containsHexPrefix(input)) {
            return input.substring(2);
        } else {
            return input;
        }
    }
    public static boolean containsHexPrefix(String input) {
        return input.length() > 1 && input.charAt(0) == '0' && input.charAt(1) == 'x';
    }
}
