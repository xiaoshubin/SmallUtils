package com.smallcake.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project20180408 --  cn.com.smallcake_utils
 * Created by Small Cake on  2018/5/4 15:50.
 * 分享工具类
 *
 * 注意：
 * 1.分享图片需要
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 *
 * 2.由于分享功能是使用隐式调用Activtiy实现的，则需在响应的Activity中声明intent-filter，在对应的activity的xml里加上
 *
  <intent-filter>
 <action android:name="android.intent.action.SEND" />
 <category android:name="android.intent.category.DEFAULT" />
 </intent-filter>

 3.开启另一个分享Acivity需要Activity的上下文
 */
public class ShareUtils {
    /**
     * 分享文字内容
     *
     * @param dlgTitle
     *            分享对话框标题
     * @param subject
     *            主题
     * @param content
     *            分享内容（文字）
     */
    public static void shareText(Activity activity,String dlgTitle, String subject, String content) {
        if (content == null || "".equals(content)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        if (subject != null && !"".equals(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);

        // 设置弹出框标题
        if (dlgTitle != null && !"".equals(dlgTitle)) { // 自定义标题
            activity.startActivity(Intent.createChooser(intent, dlgTitle));
        } else { // 系统默认标题
            activity.startActivity(intent);
        }
    }

    /**
     * 分享图片和文字内容
     *
     * @param dlgTitle 分享对话框标题 ：弹出的分享Dialog上面标题
     * @param subject            主题：一般只有发送邮件才会用到此参数
     * @param content 分享内容（文字）：微博才有
     * @param uri 图片资源URI
     */
    public static void shareImg(Activity activity,String dlgTitle, String subject, String content,
                          Uri uri) {
        if (uri == null) return;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        if (subject != null && !"".equals(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        if (content != null && !"".equals(content)) {
            intent.putExtra(Intent.EXTRA_TEXT, content);
        }

        // 设置弹出框标题
        if (dlgTitle != null && !"".equals(dlgTitle)) { // 自定义标题
            activity.startActivity(Intent.createChooser(intent, dlgTitle));
        } else { // 系统默认标题
            activity.startActivity(intent);
        }
    }
    public static void shareImg(Activity activity,String dlgTitle, String subject, String content, Bitmap bitmap) {
        Uri uri = saveBitmap(bitmap,content);if (uri == null) return;
        shareImg(activity,dlgTitle,subject,content,uri);
    }

    /** * 将图片存到本地 不能保存到缓存路径，因为只有本应用才能访问*/
    private static Uri saveBitmap(Bitmap bm,String imgName) {
        try {
            String dir=SdUtils.getCameraPath()+TimeUtils.getTime()+"_"+imgName+".jpg";
            File f = new File(dir);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Uri uri = Uri.fromFile(f);
            return uri;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();    }
        return null;
    }


    /**
     * 分享多张图片
     */

    public static void shareMultipleImage(Activity activity,File ...files) {
        ArrayList<Uri> uriList = new ArrayList<>();
        for (File file:files) uriList.add(Uri.fromFile(file));
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
        shareIntent.setType("image/*");
        activity.startActivity(Intent.createChooser(shareIntent, "分享到"));
    }
}
