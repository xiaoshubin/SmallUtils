package com.smallcake.utils;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;

/**
 * Android指纹识别,需要加入权限
 * <uses-permission android:name="android.permission.USE_FINGERPRINT" />
 * 需要API Level 23，如果达不到不会报错，这些类会什么都不做。
 * 因为Api中规定了如果回调了Error或者Succeed方法之后，sensor会被关闭
 * 直到下一次重新调用authenticate方法授权
 */

public class FingerprintUtil {
    public final static int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 0x008;//连线密码输入正确后回调Code
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void callFingerPrint(final Context context, final OnCallBackListenr listener) {
        FingerprintManagerCompat managerCompat = FingerprintManagerCompat.from(context);


        if (!managerCompat.isHardwareDetected()) { //判断设备是否支持
            listener.onFailed("当前设备不支持指纹解锁！");
            return;
        }
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(context.KEYGUARD_SERVICE);
        if (!keyguardManager.isKeyguardSecure()) {//判断设备是否处于安全保护中
            listener.onFailed("该设备未处于安全保护中！");
            return;
        }
        if (!managerCompat.hasEnrolledFingerprints()) { //判断设备是否已经注册过指纹
            listener.onFailed("未设置指纹解锁！");
            return;
        }
        managerCompat.authenticate(null, 0, null, new MyCallBack(listener), null);
    }


    public static class MyCallBack extends FingerprintManagerCompat.AuthenticationCallback{
        OnCallBackListenr listener;
        public MyCallBack(OnCallBackListenr listener) {
            this.listener = listener;
        }
        @Override//【多次】尝试后失败，errString是错误信息，如小米的errString：【尝试次数过多，请稍后再试。】
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            if (listener != null) listener.onError(errMsgId, errString);
        }
        @Override //指纹验证失败有的时候会有帮助信息：如小米的helpString：手指移动太快，请重试
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            if (listener != null) listener.onFailed(helpString);
        }
        @Override // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
        public void onAuthenticationFailed() {
            if (listener != null) listener.onFailed("指纹验证失败");
        }
        @Override// 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
            if (listener != null) listener.onSucceeded(result);
        }
    }

    public interface OnCallBackListenr {
        void onError(int errMsgId, CharSequence errString);//多次失败，不再触发指纹识别，可考虑关闭或进入连线密码
        void onFailed(CharSequence errString);//指纹识别失败
        void onSucceeded(FingerprintManagerCompat.AuthenticationResult result);
    }

    /**
     * 如果多次指纹解锁失败，调用此方法，跳转到【锁屏密码界面】
     * @param context
     * 如果锁屏秘密正确，需要再对应Activity中加入回调
     * 注意: 回调判定条件一定要加 resultCode==RESULT_OK，否则用户按返回键也会说成功
     *
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==FingerprintUtil.REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS&&resultCode==RESULT_OK)L.i("连线密码锁解锁成功！");
        }
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setKeyguardLockScreenManager(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        if (keyguardManager != null) {
            boolean keyguardSecure = keyguardManager.isKeyguardSecure();//是否拥有锁屏密码
            if (keyguardSecure) {
                Intent intent = keyguardManager.createConfirmDeviceCredentialIntent("锁屏密码", "输入锁屏密码");
                if (intent != null){
                    //开启页面回调
                    ((Activity)context).startActivityForResult(intent, REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS);
                }
            } else {
                ToastUtil.showLong("系统没有设置锁屏密码，请设置！");
                context.startActivity(new Intent("android.settings.SETTINGS").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        }
    }
}
