package cn.com.smallcake_utils;

import android.content.res.Resources;

public class IdentifierUtils {
    public static int getDrawResourceID(String resourceName) {
        Resources res=SmallUtils.getApp().getResources();
        int picid = res.getIdentifier(resourceName,"drawable",AppUtils.getAppPackageName());
        return picid;
    }
    public static int getMipmapResourceID(String resourceName) {
        Resources res=SmallUtils.getApp().getResources();
        int picid = res.getIdentifier(resourceName,"mipmap",AppUtils.getAppPackageName());
        return picid;
    }

}
