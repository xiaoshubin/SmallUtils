package com.smallcake.utils;

import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 接受三种类型的msg
 * String ,int ,CharSequence
 */
public class ToastUtil {

    private ToastUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void showLong( Object message) {
        show(SmallUtils.getApp(),message, android.widget.Toast.LENGTH_LONG);
    }
    public static void showShort( Object message) {
        show(SmallUtils.getApp(),message, android.widget.Toast.LENGTH_SHORT);
    }

    public static void showGravityLong( Object message, int gravity) {
       showGravity(SmallUtils.getApp(),message,gravity, android.widget.Toast.LENGTH_LONG);
    }
    public static void showGravityShort( Object message, int gravity) {
       showGravity(SmallUtils.getApp(),message,gravity, android.widget.Toast.LENGTH_SHORT);
    }

    public static void showMyToast(String message, final int cnt) {
        final android.widget.Toast toast = android.widget.Toast.makeText(SmallUtils.getApp(), message, android.widget.Toast.LENGTH_LONG);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3500);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt );
    }



    private static void show(Context context,Object message,int duration){
        if (message instanceof String){
            android.widget.Toast.makeText(SmallUtils.getApp(),(String)message, duration).show();
        }else if (message instanceof Integer){
            android.widget.Toast.makeText(SmallUtils.getApp(),(int)message, duration).show();
        }else if (message instanceof CharSequence){
            android.widget.Toast.makeText(SmallUtils.getApp(),(CharSequence)message, duration).show();
        }else{
            printErr(message);
        }
    }
    private static void showGravity(Context context,Object message, int gravity,int duration){
        android.widget.Toast toast = null;
        if (message instanceof String){
            toast = android.widget.Toast.makeText(SmallUtils.getApp(),(String)message, duration);
        }else if (message instanceof Integer){
            toast = android.widget.Toast.makeText(SmallUtils.getApp(),(int)message, duration);
        }else if (message instanceof CharSequence){
            toast = android.widget.Toast.makeText(SmallUtils.getApp(),(CharSequence)message, duration);
        }else{
            printErr(message);
            return;
        }
        toast.setGravity(gravity,0, (int) (64 * context.getResources().getDisplayMetrics().density + 0.5));
        toast.show();
    }
    private static void printErr(Object message) {
        try {
            throw new UnsupportedOperationException(message+" must be String|int|CharSequence");
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }




}
