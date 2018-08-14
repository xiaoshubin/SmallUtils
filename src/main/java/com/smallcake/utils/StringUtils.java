package com.smallcake.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	/**
	 * 功能：身份证的有效验证
	 *
	 * @param IDStr
	 *            身份证号
	 * @return 有效：返回"" 无效：返回String信息
	 * @throws ParseException
	 */
	public static boolean IDCardValidate(String IDStr) throws ParseException {
	        String errorInfo = "";// 记录错误信息
	        String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
	                "3", "2" };  
	        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
	                "9", "10", "5", "8", "4", "2" };  
	        String Ai = "";
	        // ================号码的长度 15位或18位 ================
	        if (IDStr.length() != 15 && IDStr.length() != 18) {  
	            errorInfo = "身份证号码长度应该为15位或18位。";
	            return false;  
	        }  
	        // =======================(end)========================  
	  
	        // ================ 数字 除最后以为都为数字================
	        if (IDStr.length() == 18) {  
	            Ai = IDStr.substring(0, 17);  
	        } else if (IDStr.length() == 15) {  
	            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);  
	        }  
	        if (isNumeric(Ai) == false) {  
	            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
	            return false;  
	        }  
	        // =======================(end)========================  
	  
	        // ================ 出生年月是否有效 ================
	        String strYear = Ai.substring(6, 10);//  年份
	        String strMonth = Ai.substring(10, 12);// 月份
	        String strDay = Ai.substring(12, 14);// 月份
	        if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {  
	            errorInfo = "身份证生日无效。";
	            return false;  
	        }  
	        GregorianCalendar gc = new GregorianCalendar();
	        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
	        try {  
	            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
	                    || (gc.getTime().getTime() - s.parse(  
	                            strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {  
	                errorInfo = "身份证生日不在有效范围。";
	                return false;  
	            }  
	        } catch (NumberFormatException e) {
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (ParseException e) {
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
	            errorInfo = "身份证月份无效";
	            return false;  
	        }  
	        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
	            errorInfo = "身份证日期无效";
	            return false;  
	        }  
	        // =====================(end)=====================  
	  
	        // ================ 地区码时候有效================
	        Hashtable h = GetAreaCode();
	        if (h.get(Ai.substring(0, 2)) == null) {  
	            errorInfo = "身份证地区编码错误。";
	            return false;  
	        }  
	        // ==============================================  
	  
	        // ================ 判断最后一位的值================
	        int TotalmulAiWi = 0;  
	        for (int i = 0; i < 17; i++) {  
	            TotalmulAiWi = TotalmulAiWi  
	                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
	                    * Integer.parseInt(Wi[i]);
	        }  
	        int modValue = TotalmulAiWi % 11;  
	        String strVerifyCode = ValCodeArr[modValue];
	        Ai = Ai + strVerifyCode;  
	  
	        if (IDStr.length() == 18) {  
	            if (Ai.equals(IDStr) == false) {  
	                errorInfo = "身份证无效，不是合法的身份证号码";
	                return false;  
	            }  
	        } else {  
	            return true;  
	        }  
	        // =====================(end)=====================  
	        return true;  
	    }
	/**
	 * 功能：判断字符串是否为数字
	 *
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
	        Pattern pattern = Pattern.compile("[0-9]*");
	        Matcher isNum = pattern.matcher(str);
	        if (isNum.matches()) {  
	            return true;  
	        } else {  
	            return false;  
	        }  
	    }
	/**
	 * 功能：设置地区编码
	 *
	 * @return Hashtable 对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	    private static Hashtable GetAreaCode() {
	        Hashtable hashtable = new Hashtable();
	        hashtable.put("11", "北京");
	        hashtable.put("12", "天津");
	        hashtable.put("13", "河北");
	        hashtable.put("14", "山西");
	        hashtable.put("15", "内蒙古");
	        hashtable.put("21", "辽宁");
	        hashtable.put("22", "吉林");
	        hashtable.put("23", "黑龙江");
	        hashtable.put("31", "上海");
	        hashtable.put("32", "江苏");
	        hashtable.put("33", "浙江");
	        hashtable.put("34", "安徽");
	        hashtable.put("35", "福建");
	        hashtable.put("36", "江西");
	        hashtable.put("37", "山东");
	        hashtable.put("41", "河南");
	        hashtable.put("42", "湖北");
	        hashtable.put("43", "湖南");
	        hashtable.put("44", "广东");
	        hashtable.put("45", "广西");
	        hashtable.put("46", "海南");
	        hashtable.put("50", "重庆");
	        hashtable.put("51", "四川");
	        hashtable.put("52", "贵州");
	        hashtable.put("53", "云南");
	        hashtable.put("54", "西藏");
	        hashtable.put("61", "陕西");
	        hashtable.put("62", "甘肃");
	        hashtable.put("63", "青海");
	        hashtable.put("64", "宁夏");
	        hashtable.put("65", "新疆");
	        hashtable.put("71", "台湾");
	        hashtable.put("81", "香港");
	        hashtable.put("82", "澳门");
	        hashtable.put("91", "国外");
	        return hashtable;  
	    }

	/**
	 * 验证日期字符串是否是YYYY-MM-DD格式
	 *
	 * @param str
	 * @return
	 */
	private static boolean isDataFormat(String str) {
	        boolean flag = false;  
	        // String  
	        // regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";  
	        String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
	        Pattern pattern1 = Pattern.compile(regxStr);
	        Matcher isNo = pattern1.matcher(str);
	        if (isNo.matches()) {  
	            flag = true;  
	        }  
	        return flag;  
	    }


	//2.判断字符串是否是邮箱：
	/**
	 * 描述：是否是邮箱.
	 *
	 * @param str 指定的字符串
	 * @return 是否是邮箱:是为true，否则false
	 */
	 	public static Boolean isEmail(String str) {
	 		Boolean isEmail = false;
	 		String expr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	 		if (str.matches(expr)) {
	 			isEmail = true;
	 		}
	 		return isEmail;
	 	}
	//3.判断字符串是否是银行卡
	/**
	 * 判断是否是银行卡号
	 * @param cardId
	 * @return
	 */
	    public static boolean checkBankCard(String cardId) {
	        char bit = getBankCardCheckCode(cardId  
	                .substring(0, cardId.length() - 1));  
	        if (bit == 'N') {  
	            return false;  
	        }  
	        return cardId.charAt(cardId.length() - 1) == bit;  
	  
	    }  
	    
	    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
	        if (nonCheckCodeCardId == null  
	                || nonCheckCodeCardId.trim().length() == 0  
	                || !nonCheckCodeCardId.matches("\\d+")) {
				// 如果传的不是数据返回N
	            return 'N';  
	        }  
	        char[] chs = nonCheckCodeCardId.trim().toCharArray();  
	        int luhmSum = 0;  
	        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {  
	            int k = chs[i] - '0';  
	            if (j % 2 == 0) {  
	                k *= 2;  
	                k = k / 10 + k % 10;  
	            }  
	            luhmSum += k;  
	        }  
	        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');  
	    }
	//4、判断字符串是否是手机号
	/**
	 * 判断是否是手机号
	 * @param phone
	 * @return
	 */
	    public static boolean checkPhone(String phone) {
	        Pattern pattern = Pattern
	                .compile("^(13[0-9]|15[0-3]|15[5-9]|18[0-9]|14[57]|17[0678])\\d{8}$");
	        Matcher matcher = pattern.matcher(phone);
	  
	        if (matcher.matches()) {  
	            return true;  
	        }  
	        return false;  
	    }
	//5.判断字符串是否是中文或者包含中文
	/**
	 * 描述：判断一个字符串是否为null或空值.
	 *
	 * @param str 指定的字符串
	 * @return true or false
	 */
	    public static boolean isEmpty(String str) {
	        return str == null || str.trim().length() == 0;
	    }

	/**
	 * 描述：是否是中文.
	 *
	 * @param str 指定的字符串
	 * @return  是否是中文:是为true，否则false
	 */
	    public static Boolean isChinese(String str) {
	    	Boolean isChinese = true;
	        String chinese = "[\u0391-\uFFE5]";
	        if(!isEmpty(str)){
				//获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
		         for (int i = 0; i < str.length(); i++) {
					 //获取一个字符
		             String temp = str.substring(i, i + 1);
					 //判断是否为中文字符
		             if (temp.matches(chinese)) {
		             }else{
		            	 isChinese = false;
		             }
		         }
	        }
	        return isChinese;
	    }

	/**
	 * 描述：是否包含中文.
	 *
	 * @param str 指定的字符串
	 * @return  是否包含中文:是为true，否则false
	 */
	    public static Boolean isContainChinese(String str) {
	    	Boolean isChinese = false;
	        String chinese = "[\u0391-\uFFE5]";
	        if(!isEmpty(str)){
				//获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
		         for (int i = 0; i < str.length(); i++) {
					 //获取一个字符
		             String temp = str.substring(i, i + 1);
					 //判断是否为中文字符
		             if (temp.matches(chinese)) {
		            	 isChinese = true;
		             }else{
		            	 
		             }
		         }
	        }
	        return isChinese;
	    }
}
