package com.smallcake.utils;

import java.io.File;

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


}
