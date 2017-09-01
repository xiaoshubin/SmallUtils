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
     * sd卡获取根目录路径
     * @return /storage/emulated/0/
     */
    public static String getRootPath() {
        return Environment.getExternalStorageDirectory()+ File.separator;
    }
    /**
     * sd卡获取下载路径
     * @return /storage/emulated/0/Download/
     */
    public static String getDownloadPath() {
        return Environment.getExternalStorageDirectory()+ File.separator + Environment.DIRECTORY_DOWNLOADS+ File.separator;
    }

    /**
     * 相机存放图片路径
     * @return /storage/emulated/0/DCIM/Camera/
     */
    public static String getCameraPath(){
       return Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;
    }




}
