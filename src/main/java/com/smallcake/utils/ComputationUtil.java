package com.smallcake.utils;

import android.support.annotation.IntRange;

import java.math.BigDecimal;

/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2018/8/2 16:47.
 *
 * 多位小数的精确计算工具类
 * 使用BigDecimal，但一定要用BigDecimal(String)构造器，而千万不要用BigDecimal(double)来构造
 * （也不能将float或double型转换成String再来使用BigDecimal(String)来构造，
 * 因为在将float或double转换成String时精度已丢失）。例如new BigDecimal(0.1)，它将返回一个BigDecimal，
 * 也即0.1000000000000000055511151231257827021181583404541015625，
 * 正确使用BigDecimal，程序就可以打印出我们所期望的结果0.9
 * Java代码:
 System.out.println(new BigDecimal("2.0").subtract(new BigDecimal("1.10")));// 0.9
 另外，如果要比较两个浮点数的大小，要使用BigDecimal的compareTo方法。

 BigDecimal.setScale()方法用于格式化小数点

 BigDecimal.ROUND_UP：进位处理
 BigDecimal.ROUND_DOWN：直接删除多余的小数位
 BigDecimal.ROUND_HALF_UP:四舍五入（默认）2.35变成2.4
 BigDecimal.ROUND_HALF_DOWN：四舍五入，2.35变成2.3，如果是5则向下舍
 BigDecimal.ROUND_CEILING：接近正无穷大的舍入
 BigDecimal.ROUND_FLOOR：接近负无穷大的舍入，数字>0和ROUND_UP作用一样，数字<0和ROUND_DOWN作用一样
 BigDecimal.ROUND_HALF_EVEN：向最接近的数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入



 https://www.cnblogs.com/zouhao/p/6713230.html
 https://blog.csdn.net/ochangwen/article/details/51531866
 */

public class ComputationUtil {
    /**
     *  x 乘
     * @param a double包含了float，精确度更高
     * @param b
     * @return
     */
    public static double add(String a,String b){
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB =new BigDecimal(b);
        return  bigDecimalA.add(bigDecimalB).doubleValue();
    }
    public static double sub(String a,String b){
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB =new BigDecimal(b);
        return  bigDecimalA.subtract(bigDecimalB).doubleValue();
    }
    public static double mul(String a,String b){
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB =new BigDecimal(b);
        return  bigDecimalA.multiply(bigDecimalB).doubleValue();
    }
    public static double div(String a,String b){
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB =new BigDecimal(b);
        return  bigDecimalA.divide(bigDecimalB).doubleValue();
    }


    public static double add(double a,double b){
        return  add(Double.toString(a),Double.toString(b));
    }
    public static double sub(double a,double b){
        return  sub(Double.toString(a),Double.toString(b));
    }
    public static double mul(double a,double b){
        return  mul(Double.toString(a),Double.toString(b));
    }
    public static double div(double a,double b){
        return  div(Double.toString(a),Double.toString(b));
    }

    /**
     *
     * @param a
     * @param b
     * @param scale 保留几位小数1到8位
     * @return
     */
    public static double add(String a,String b, @IntRange(from = 1,to = 8) int scale){
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB =new BigDecimal(b);
        return  bigDecimalA.add(bigDecimalB).setScale(scale).doubleValue();
    }
    public static double sub(String a,String b, @IntRange(from = 1,to = 8) int scale){
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB =new BigDecimal(b);
        return  bigDecimalA.subtract(bigDecimalB).setScale(scale).doubleValue();
    }
    public static double mul(String a,String b, @IntRange(from = 1,to = 8) int scale){
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB =new BigDecimal(b);
        return  bigDecimalA.multiply(bigDecimalB).setScale(scale).doubleValue();
    }
    public static double div(String a,String b, @IntRange(from = 1,to = 8) int scale){
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB =new BigDecimal(b);
        return  bigDecimalA.divide(bigDecimalB,scale).doubleValue();
    }

    /**
     *  比较大小
     * 使用BigDecimal的坏处是性能比double和float差，在处理庞大，复杂的运算时尤为明显，因根据实际需求决定使用哪种类型。
        a<b:-1,a=b:0,a>b:1
     * @param a
     * @param b
     * @return -1,0,1
     */
    public static int compare(BigDecimal a,BigDecimal b){
        return  a.compareTo(b);
    }
}
