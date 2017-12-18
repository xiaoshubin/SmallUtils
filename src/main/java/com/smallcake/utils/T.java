package com.smallcake.utils;

import android.content.Context;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 接受三种类型的msg
 * String ,int ,CharSequence
 */
public class T {

    private T() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void showLong( Object message) {
        show(SmallUtils.getApp(),message,Toast.LENGTH_LONG);
    }
    public static void showShort( Object message) {
        show(SmallUtils.getApp(),message,Toast.LENGTH_SHORT);
    }

    public static void showGravityLong( Object message, int gravity) {
       showGravity(SmallUtils.getApp(),message,gravity,Toast.LENGTH_LONG);
    }
    public static void showGravityShort( Object message, int gravity) {
       showGravity(SmallUtils.getApp(),message,gravity,Toast.LENGTH_SHORT);
    }

    public static void showMyToast(String message, final int cnt) {
        final Toast toast = Toast.makeText(SmallUtils.getApp(), message, Toast.LENGTH_LONG);
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
            Toast.makeText(SmallUtils.getApp(),(String)message, duration).show();
        }else if (message instanceof Integer){
            Toast.makeText(SmallUtils.getApp(),(int)message, duration).show();
        }else if (message instanceof CharSequence){
            Toast.makeText(SmallUtils.getApp(),(CharSequence)message, duration).show();
        }else{
            printErr(message);
        }
    }
    private static void showGravity(Context context,Object message, int gravity,int duration){
        Toast toast = null;
        if (message instanceof String){
            toast = Toast.makeText(SmallUtils.getApp(),(String)message, duration);
        }else if (message instanceof Integer){
            toast = Toast.makeText(SmallUtils.getApp(),(int)message, duration);
        }else if (message instanceof CharSequence){
            toast = Toast.makeText(SmallUtils.getApp(),(CharSequence)message, duration);
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
