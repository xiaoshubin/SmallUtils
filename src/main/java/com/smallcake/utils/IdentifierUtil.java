package com.smallcake.utils;

/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2018/6/22 14:15.
 * 获取图片id，有规则的图片
 */

public class IdentifierUtil {
    public static int getIdByMipmap(String tag){
        return SmallUtils.getApp().getResources().getIdentifier(tag, "mipmap",AppUtils.getAppPackageName());
    }
}
