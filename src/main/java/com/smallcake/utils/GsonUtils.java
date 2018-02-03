package com.smallcake.utils;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

//    /**
//     * 返回List<Object>
//     * @param list
//     * @param clz
//     * @param <T>
//     * @return
//     */
//    @Deprecated
//    public static <T> List<T> gsonToList(String list, Class<T> clz){
//        List<T> lt = null;
//        if (list!=null&&!list.equals("")){
//            lt = gson.fromJson(list, new TypeToken<List<T>>(){}.getType());
//        }
//        return lt;
//    }
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
    public static Map<String, String> jsonToMapString(String gsonString) {
        Map<String, String> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, String>>() {
            }.getType());
        }
        return map;
    }

    /**
     * 专门针对后台奇葩数据集，明明是数组对象，非要做成map键值对,
     * 而这里的1，3，8毫无用处，后台说工具生成的，没办法
     * 先转JsonObject，通过再转List
     * @param gsonString
     * @param cls
     * @param <T>
     * @return List<T>
     *
     * 丢弃掉无用的1，3，8，把后面的value转为对象，再添加到List
     *
     *   "catArr": {
                "1": {
                "member_cat_id": "11",
                "member_cat_name": "区县服务中心",
                "member_cat_amount": "100000.00"
                },
                "3": {
                "member_cat_id": "12",
                "member_cat_name": "地市级代理",
                "member_cat_amount": "500000.00"
                },
                "8": {
                "member_cat_id": "13",
                "member_cat_name": "一级代理",
                "member_cat_amount": "2000000.00"
                }
         }

     *
     * @注意 目前缺陷，不能强转类型，比如"100000.00"，你在对象中定义此类型为String和double可以，
     * 你知道这个参数肯定是int类型，不会有小数，但如果你定义为int，就会类型转换错误
     * 而gson在外面直接转对象是可以定义为int的,在这里由于用到了泛型T，所以行不通
     *
     */
    public static <T> List<T> jsonToMapsToList(String gsonString, Class<T> cls) {

        List<T> list = new ArrayList<T>();
        JsonObject asJsonObject =  new JsonParser().parse(gsonString).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entries = asJsonObject.entrySet();
        for (Map.Entry<String, JsonElement> map: entries){
            T t = gson.fromJson(map.getValue(),cls);
            list.add(t);
        }

        return list;
    }

}
