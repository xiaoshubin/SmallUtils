package com.smallcake.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

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
    public static boolean isEmpty(EditText... editTexts) {
        return  isEmpty(null,editTexts);
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
    public static boolean isEmpty(Context context,EditText... editTexts) {
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
                    if (context!=null)T.showLong(hintStr);
                }else{
                    editText.setError("未填写");
                    if (context!=null)T.showLong("有选项未填写");
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
                String s = cs.toString();
                if (s.contains(".")){
                     if (s.length() - s.indexOf(".") > 3) {//indexOf索引从0开始数
                         s = s.substring(0,s.indexOf(".") + 3);
                         editText.setText(s);//去掉多于位数数字，重新设置字符
                         editText.setSelection(s.length());//光标到最后
                      }
                        //如果用户第一位输入的是【.】,则前面加0
                      if (s.trim().length()==1){
                          editText.setText("0.");
                          editText.setSelection(2);
                      }
                }
                //如果第一位输入的是0，第二位不是小数点,那么直接去掉前面的0，如08，直接变成8
                if (s.startsWith("0")&&s.trim().length()>1&&s.indexOf(".")!=1){
                    editText.setText(s.subSequence(1, 2));
                    editText.setSelection(1);

                }
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
        });

    }


}
