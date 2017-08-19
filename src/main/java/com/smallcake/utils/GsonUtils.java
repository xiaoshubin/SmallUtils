package com.smallcake.utils;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MyApplication --  cq.cake.util
 * Created by Small Cake on  2017/8/16 13:35.
 * 需要导入
 * compile 'com.google.code.gson:gson:2.8.1'
 *
 * gsonToList方法可能导致泛型在编译期类型被擦除导致报错,建议使用jsonToList方法
 *
 */

public class GsonUtils {
    private static Gson gson = null;
    static {//静态代码块，是在类中独立于类成员的static语句块，每个代码块只会被执行一次
        if (gson == null) {
            gson = new Gson();
        }
    }

    /**
     * 返回List<Object>
     * @param list
     * @param clz
     * @param <T>
     * @return
     */
    @Deprecated
    public static <T> List<T> gsonToList(String list, Class<T> clz){
        List<T> lt = null;
        if (list!=null&&!list.equals("")){
            lt = gson.fromJson(list, new TypeToken<List<T>>(){}.getType());
        }
        return lt;
    }
    /**
     * 转成list
     * 解决泛型编译期类型被擦除的问题
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * 直接返回Object
     * @param objStr
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T jsonToObj(String objStr,Class<T> clz){
        T t = null;
        if (objStr!=null) t = gson.fromJson(objStr,clz);
        return t;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> jsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;

    }
    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> jsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

}
