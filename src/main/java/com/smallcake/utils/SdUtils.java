package com.smallcake.utils;

import android.os.Environment;

import java.io.File;

/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2017/8/17 11:18.
 * sd卡路径获取工具类
 */

public class SdUtils {
    /**
     * sd卡路径
     * @return
     */
    public static File getPathFile() {
        return Environment.getExternalStorageDirectory();
    }

    /**
     *  Sd卡下载路径
     * @return
     */
    public static File getDownloadPathFile() {
        return Environment.getDownloadCacheDirectory();
    }

    public static File getDataPath(){
        return Environment.getDataDirectory();
    }


}
