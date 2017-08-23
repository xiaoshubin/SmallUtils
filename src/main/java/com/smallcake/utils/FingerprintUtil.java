package com.smallcake.utils;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;



/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2017/8/23 13:41.
 * Android指纹识别
 * 需要加入权限
 * <uses-permission android:name="android.permission.USE_FINGERPRINT" />
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintUtil {
    private static CancellationSignal cancellationSignal;//清除指纹识别
    public final static int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 0x008;//连线密码输入正确后回调Code
    private static KeyguardManager keyguardManager;//连线键盘锁管理器



    public static void callFingerPrint(final Context context, final OnCallBackListenr listener) {
        FingerprintManagerCompat managerCompat = FingerprintManagerCompat.from(context);
        if (!managerCompat.isHardwareDetected()) { //判断设备是否支持
            if (listener != null) listener.onSupportFailed();
            return;
        }
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(context.KEYGUARD_SERVICE);
        if (!keyguardManager.isKeyguardSecure()) {//判断设备是否处于安全保护中
            if (listener != null) listener.onInsecurity();
            return;
        }
        if (!managerCompat.hasEnrolledFingerprints()) { //判断设备是否已经注册过指纹
            if (listener != null)listener.onEnrollFailed(); //未注册
            return;
        }
        if (listener != null) listener.onAuthenticationStart(); //开始指纹识别

        cancellationSignal = new CancellationSignal(); //必须重新实例化，否则cancel 过一次就不能再使用了
        managerCompat.authenticate(null, 0, cancellationSignal, new FingerprintManagerCompat.AuthenticationCallback() {
            @Override//多次尝试后失败，errString是错误信息，如小米的errString：尝试次数过多，请稍后再试。
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                if (listener != null) listener.onAuthenticationError(errMsgId, errString);
            }

            @Override // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
            public void onAuthenticationFailed() {
                if (listener != null) listener.onAuthenticationFailed();
            }

            @Override //指纹验证失败有的时候会有帮助信息：如小米的helpString：手指移动太快，请重试
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                if (listener != null) listener.onAuthenticationHelp(helpMsgId, helpString);

            }


            @Override// 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {

                if (listener != null) {
                    listener.onAuthenticationSucceeded(result);
                }
                cancel();

            }
        }, null);
    }

    public interface OnCallBackListenr {

        void onSupportFailed();//当前设备不支持指纹解锁

        void onInsecurity();//当前设备未处于安全保护中

        void onEnrollFailed();//请到设置中设置指纹,未设置指纹解锁

        void onAuthenticationStart();//开始指纹识别监听

        void onAuthenticationError(int errMsgId, CharSequence errString);//多次尝试失败后异常

        void onAuthenticationFailed();//指纹识别失败回调

        void onAuthenticationHelp(int helpMsgId, CharSequence helpString);//提醒帮助

        void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result);//成功
    }

    /**
     * 取消指纹监听
     * 1.指纹识别成功后，取消
     * 2.多次识别失败后，进入锁屏密码界面，也需要取消
     * 否则，指纹识别还在，再次开启指纹识别会立即判定为【多次指纹识别失败】
     */
    private static void cancel() {
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
            cancellationSignal = null;
        }
    }

    /**
     * 如果多次指纹解锁失败，调用此方法，跳转到锁屏密码界面
     * @param context
     * 如果锁屏秘密正确，需要再对应Activity中加入回调
     * 注意: 判定条件一定要加&&resultCode==RESULT_OK，否则用户按返回键也会说成功
     *
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==FingerprintUtil.REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS&&resultCode==RESULT_OK)L.i("连线密码锁解锁成功！");
        }
     */
    public static void setKeyguardLockScreenManager(Context context) {
        keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        if (keyguardManager != null) {

            boolean keyguardSecure = keyguardManager.isKeyguardSecure();//是否拥有锁屏密码
            if (keyguardSecure) {
                Intent intent = keyguardManager.createConfirmDeviceCredentialIntent("锁屏密码", "输入锁屏密码");
                if (intent != null){
                    //开启页面回调
                    ((Activity)context).startActivityForResult(intent, REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS);
                }
            } else {
                T.showLong(context,"系统没有设置锁屏密码，请设置！");
                context.startActivity(new Intent("android.settings.SETTINGS").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        }
    }

    /**
     * 锁屏密码解锁成功，调用清除方法
     */
    public static  void clearKeyguardManager() {
        if (keyguardManager != null) keyguardManager = null;
    }

}
