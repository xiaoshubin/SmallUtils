package com.smallcake.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2017/9/7 17:20.
 * show notification
 * just a simple example
 * maybe you can write more each other notice
 */

public class NotificationUtils {
    /**
     * once or twice
     * @param context
     * @param ticker
     * @param smallIcon
     * @param title
     * @param msg
     */
    public static void showNotice(Context context, String ticker, int smallIcon, CharSequence title, CharSequence msg){
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context)
                .setTicker(ticker)
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setWhen(System.currentTimeMillis())
                .setContentText(msg)
                .setAutoCancel(false)
                .setDefaults(Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND)
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(0,notification);
    }

    /**
     * use for progress notification
     * @param context
     * @param smallIcon
     * @param title
     * @param msg
     * @param progress
     */
    public static void showNoticeProgress(Context context, int smallIcon, CharSequence title, CharSequence msg,int progress){
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setWhen(System.currentTimeMillis())
                .setContentText(msg)
                .setAutoCancel(false)
                .setDefaults(Notification.DEFAULT_LIGHTS)
                .setProgress(100,progress,false)
                .setOngoing(true).build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(0,notification);

    }
}
