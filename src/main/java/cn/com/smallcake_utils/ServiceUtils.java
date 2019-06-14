package cn.com.smallcake_utils;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;

import java.util.List;

/**
 * MyApplication --  cn.com.smallcake_utils
 * Created by Small Cake on  2017/8/30 17:43.
 */

public class ServiceUtils {
    /**
     * 判断当前service是否还在运行
     * 用于页面解除绑定前的判断，多次解绑将会异常
     * @param mContext
     * @param serviceName
     * @return
     */
    public static <T extends Service>boolean isServiceRunning(Context mContext, Class<T>  serviceName) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(30);
        if (!(serviceList.size()>0)) return false;

        for (int i=0; i<serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(getServiceName(serviceName)) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    public static <T extends Service>String getServiceName(Class<T> clz){
        return  clz.getName();
    }


}
