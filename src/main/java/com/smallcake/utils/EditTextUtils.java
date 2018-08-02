package com.smallcake.utils;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2017/8/24 11:09.
 * 编辑输入工具类
 * 依赖工具： T ,L
 */

public class EditTextUtils {
    private final static String TAG = EditTextUtils.class.getSimpleName();
    /**
     * 编辑框警告提示，例：
     * if(EditTextUtils.isEmpty(etRname, etNo, etAddr))return;
     *
     */
    @Deprecated
    public static boolean isEmpty(Context context,EditText... editTexts) {
        return  isEmpty(editTexts);
    }
    /**
     * 获取数据集合
     * @param editTexts
     * @return
     */
    public static String[] getEditTextInfos(EditText... editTexts) {
        String [] infos = new String[editTexts.length];
        for (int i = 0; i < editTexts.length; i++) {
            String info = editTexts[i].getText().toString();
            infos[i] = info;
        }

        return infos;

    }
    /**
     * 验证多个输入栏未输入，提示为：（"请填写" + 你xml设置的Hint字段）
     * @param editTexts 编辑框
     * @return 双项提示，编辑框警告提示，弹出提示
     * 例：
     * if(EditTextUtils.isEmpty(this,etRname, etNo, etAddr))return;
     *
     */
    public static boolean isEmpty(EditText... editTexts) {
        for (EditText editText : editTexts) {
            String str = editText.getText().toString();
            String hintStr="";
            try {
                hintStr = editText.getHint().toString();
            } catch (Exception e) {
                e.printStackTrace();
                L.e(TAG,editText+" 未设置 android:hint=\"\" 属性");
            }
            if (TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(hintStr)){
                    editText.setError( hintStr);
                    T.showLong(hintStr);
                }else{
                    editText.setError("未填写");
                    T.showLong("有选项未填写");
                }

                return true;
            }
        }
        return false;

    }

    /**
     * 设置EditText只能输入两位小数 48.88
     * @param editText 编辑框
     */
    public static void setTwoDecimalPlaces(final EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length()>0){

                    String temp = editable.toString();
                    int posDot = temp.indexOf(".");
                    if (posDot <= 0) return;
                    if (temp.length() - posDot - 1 > 2) {
                        editable.delete(posDot + 3, posDot + 4);
                    }
                }
            }
        });

    }

    /**
     * 设置小数位
     * @param editText
     */
    public static void setDecimalPlaces(final EditText editText, final int number){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
            }
            @Override
            public void beforeTextChanged(CharSequence cs, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length()>0){
                    String temp = editable.toString();
                    int posDot = temp.indexOf(".");
                    if (posDot <= 0) return;
                    if (temp.length() - posDot - 1 > number) {
                        editable.delete(posDot + 3, posDot + 4);
                    }
                }
            }
        });

    }

    /**
     * 是纯数字
     */
    public static boolean isJustNumber(EditText editText){
        String str = editText.getText().toString().trim().replaceAll(" ", "");
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(str);
        return m.matches();
    }
    /**
     * 是纯字母
     */
    public static boolean isJustLetter(EditText editText){
        String str = editText.getText().toString().trim().replaceAll(" ", "");
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 是否输入了特殊字符
     * @param str
     * @return
     */
    public static boolean compileExChar(String str){
        String limitEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(limitEx);
        Matcher m = pattern.matcher(str);
        return m.find();
    }

    /**
     * 过滤表情和特殊字符
     * @param et
     */
    public static void setEtFilter(EditText et) {
        if (et == null) return;
        //表情过滤器
        InputFilter emojiFilter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                                       int dstart, int dend) {
                Pattern emoji = Pattern.compile(
                        "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                        Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                    return "";
                }
                return null;
            }
        };
        //特殊字符过滤器
        InputFilter specialCharFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String regexStr = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(regexStr);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.matches()) {
                    return "";
                } else {
                    return null;
                }

            }
        };

        et.setFilters(new InputFilter[]{emojiFilter, specialCharFilter});
    }

    /**
     * 过滤中文
     * @param et
     */
    public static void setEtFilterChinese(EditText et) {
        if (et == null) return;
        //中文过滤器
        InputFilter emojiFilter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                                       int dstart, int dend) {
                Pattern emoji = Pattern.compile("[\\u4e00-\\u9fa5]");
                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                    return "";
                }
                return null;
            }
        };
        et.setFilters(new InputFilter[]{emojiFilter});
    }

}
