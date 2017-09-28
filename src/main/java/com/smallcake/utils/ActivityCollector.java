package com.smallcake.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * add and remove Activity
 * use this into BaseActivity
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static <T extends Activity>void finishActivity(Class<T> activity) {
        if (activities!=null&&activities.size()>0)for (Activity a:activities){
            if ((a.getClass().getName()).equals(activity.getName()))a.finish();
        }
    }
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) activity.finish();
        }
    }
}
