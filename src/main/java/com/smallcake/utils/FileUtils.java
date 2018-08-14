package com.smallcake.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2017/8/31 15:52.
 */

public class FileUtils {
    /**
     * 创建文件夹,如果不存在的话
     * @param filePath
     * @return
     */
    public static boolean makeDirs(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return false;
        }
        File folder = new File(filePath);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }

    /**
     * 创建一个文件的上级目录，如果不存在的话
     * @param filePath
     * @return
     */
    public static boolean makeParentDirs(String filePath,String fileName) {
        if (filePath == null || filePath.isEmpty()) {
            return false;
        }
        File folder = new File(filePath,fileName);
        return (folder.getParentFile().exists() && folder.getParentFile().isDirectory()) ? true : folder.getParentFile().mkdirs();
    }

    /**
     * 打开指定路径目录
     * @param path
     */
    public static void openFilePath(Context context,String path){

        //getUrl()获取文件目录，例如返回值为/storage/sdcard1/MIUI/music/mp3_hd/单色冰淇凌_单色凌.mp3
        File file = new File(path);
        //获取父目录
        File parentFlie = new File(file.getParent());
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setDataAndType(Uri.fromFile(parentFlie), "*/*");
        context.startActivity(intent);

    }

    /**
     * 获取Assets路径下的文件
     *
     * @param fileName
     * @return
     */
    public static String getJson( String fileName) {

        String json = "";

        try {

            AssetManager s = SmallUtils.getApp().getAssets();
            try {
                InputStream is = s.open(fileName);
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                json = new String(buffer, "utf-8");
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(json);
        return json;

    }



}
