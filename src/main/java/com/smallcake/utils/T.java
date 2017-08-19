package com.smallcake.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 接受三种类型的msg
 * String ,int ,CharSequence
 */
public class T {

    private T() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void showLong(Context context, Object message) {
        show(context,message,Toast.LENGTH_LONG);
    }
    public static void showShort(Context context, Object message) {
        show(context,message,Toast.LENGTH_SHORT);
    }

    public static void showGravityLong(Context context, Object message, int gravity) {
       showGravity(context,message,gravity,Toast.LENGTH_LONG);
    }
    public static void showGravityShort(Context context, Object message, int gravity) {
       showGravity(context,message,gravity,Toast.LENGTH_SHORT);
    }



    private static void show(Context context,Object message,int duration){
        if (message instanceof String){
            Toast.makeText(context,(String)message, duration).show();
        }else if (message instanceof Integer){
            Toast.makeText(context,(int)message, duration).show();
        }else if (message instanceof CharSequence){
            Toast.makeText(context,(CharSequence)message, duration).show();
        }else{
            printErr(message);
        }
    }
    private static void showGravity(Context context,Object message, int gravity,int duration){
        Toast toast = null;
        if (message instanceof String){
            toast = Toast.makeText(context,(String)message, duration);
        }else if (message instanceof Integer){
            toast = Toast.makeText(context,(int)message, duration);
        }else if (message instanceof CharSequence){
            toast = Toast.makeText(context,(CharSequence)message, duration);
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
