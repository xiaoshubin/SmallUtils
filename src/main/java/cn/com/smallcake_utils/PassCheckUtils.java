package cn.com.smallcake_utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project20180408 --  com.cn.utils
 * Created by Small Cake on  2018/5/28 14:07.
 * 密码验证工具
 * ^ 开始
 * $ 结束
 *
 */
public class PassCheckUtils {
    /**
     * 只含数字和字母且长度为8-16位
     * 1，不能全部是数字
     2，不能全部是字母
     3，必须是数字或字母
     4.长度为8到16位
     * @return
     */
    public static boolean isNumAndLetter(String str){
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        return str.matches(regex);
    }

    /**
     * 包含特殊字符
     * @param str
     * @return
     */
    public static boolean isContainSpecialChar(String str) {
        String regEx = "\\W";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
    /**
     * 包含中文
     */
    public static boolean isContainChinese(String str){
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 包含数字
     * @param str
     * @return
     */
    public static boolean   isContainNumeric(String str){
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
    /**
     * 纯数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        return str.matches("[0-9]+");
    }
    /**
     * 包含字母
     * @param str
     * @return
     */
    public static boolean   isContainLetter(String str){
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
    /**
     * 纯字母
     * @param str
     * @return
     */
    public static boolean isLetter(String str){
        return str.matches("[a-zA-Z]+");
    }
}
