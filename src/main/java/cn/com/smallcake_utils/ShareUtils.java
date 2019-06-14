package cn.com.smallcake_utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        if (!TextUtils.isEmpty(subject)) intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (!TextUtils.isEmpty(content)) intent.putExtra(Intent.EXTRA_TEXT, content);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        // 设置弹出框标题
        if (!TextUtils.isEmpty(dlgTitle)) { // 自定义标题
            activity.startActivity(Intent.createChooser(intent, dlgTitle));
        } else { // 系统默认标题

            activity.startActivity(intent);
        }
    }

    public static Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = SmallUtils.getApp().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Images.Media._ID }, MediaStore.Images.Media.DATA + "=? ",
                new String[] { filePath }, null);
        Uri uri = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
                Uri baseUri = Uri.parse("content://media/external/images/media");
                uri = Uri.withAppendedPath(baseUri, "" + id);
            }

            cursor.close();
        }

        if (uri == null) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DATA, filePath);
            uri = SmallUtils.getApp().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        }

        return uri;
    }
    public static ArrayList<Uri> getImageContentUris(List<File> imageFiles) {
        ArrayList<Uri> uriList = new ArrayList<>();
        for (int i = 0; i < imageFiles.size(); i++) {
            uriList.add(getImageContentUri(imageFiles.get(i)));
        }
        return uriList;
    }
    public static void shareImg(Activity activity,String dlgTitle, String subject, String content, Bitmap bitmap) {
        Uri uri = saveBitmap(bitmap,dlgTitle);if (uri == null) return;
        shareImg(activity,dlgTitle,subject,content,uri);
    }

    /** * 将图片存到本地 不能保存到缓存路径，因为只有本应用才能访问*/
    private static Uri saveBitmap(Bitmap bm,String imgName) {
        try {
            String dir=SdUtils.getCameraPath()+TimeUtils.getTime()+"_.jpg";
            File f = new File(dir);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Uri uri;
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                uri=getImageContentUri(f);//
//                 uri = FileProvider.getUriForFile(SmallUtils.getApp(), AppUtils.getAppPackageName()+".fileprovider", f);
            }else {//
                 uri = Uri.fromFile(f);
            }
            return uri;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();    }
        return null;
    }


    /**
     * 分享多张图片
     * 微信朋友圈不支持
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
