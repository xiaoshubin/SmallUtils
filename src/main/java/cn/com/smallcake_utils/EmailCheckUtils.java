package cn.com.smallcake_utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project20180408 --  cn.com.smallcake_utils
 * Created by Small Cake on  2018/6/11 13:40.
 */
public class EmailCheckUtils {

    public static boolean isEmail(String email){
        String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(email);
        return m.matches();
    }
}
