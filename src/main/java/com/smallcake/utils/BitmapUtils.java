package com.smallcake.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * MyApplication --  com.smallcake.utils
 * Created by Small Cake on  2017/8/15 11:02.
 *
 * 其中，A代表透明度；R代表红色；G代表绿色；B代表蓝色。
 *
 * ALPHA_8 表示8位Alpha位图,即A=8,一个像素点占用1个字节,它没有颜色,只有透明度
 *
 * ARGB_4444 表示16位ARGB位图，即A=4,R=4,G=4,B=4,一个像素点占4+4+4+4=16位，2个字节
 *
 * ARGB_8888 表示32位ARGB位图，即A=8,R=8,G=8,B=8,一个像素点占8+8+8+8=32位，4个字节
 *
 * RGB_565 表示16位RGB位图,即R=5,G=6,B=5,它没有透明度,一个像素点占5+6+5=16位，2个字节
 *
 * @注意 android手机中，图片的所占的内存大小和很多因素相关，计算起来也很麻烦。
 * 为了计算出一个图片的内存大小，可以将图片当做一个文件来间接计算，如方法：file.length() / 1024
 * 或转为文件流FileInputStream来计算  fis.available() / 1024
 */

public class BitmapUtils {
    /**
     * 路径获取图片转Bitmap
     * @param imgPath
     * @return
     */
    public static Bitmap getBitmapPath(String imgPath) {
        return BitmapFactory.decodeFile(imgPath);
    }

    /**
     * 从资源id中获取Bitmap
     * @param context
     * @param res
     * @return
     */
    public static Bitmap getBitmapRes(Context context,int res){
       return BitmapFactory.decodeResource(context.getResources(), res);
    }

    /**
     * 保存位图图像到指定的路径
     *
     * @param bitmap
     * @param outPath
     * @throws FileNotFoundException
     */
    public static void saveBitmap(Bitmap bitmap, String outPath) throws FileNotFoundException {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(outPath));
    }

    /**
     * 打印获取的Bitmap大小和尺寸
     * @param bitmap
     */
    public static void printBitmapSize(Bitmap bitmap){
        Log.i("BitmapUtils", "Bitmap的大小【" + bitmap.getByteCount() + "】byte 宽度【" + bitmap.getWidth() + "】高度【" + bitmap.getHeight()+"】");
    }

    /**
     * TODO 1 质量压缩 ,bitmap所占内存大小是不会变的,返回字节数组
     * 不会减少图片的像素，保持像素的前提下改变图片的位深及透明度等
     *
     * 但是我们看到bytes.length是随着quality变小而变小的。
     * 这样适合去传递二进制的图片数据，比如[微信分享图片]，要传入二进制数据过去，限制32kb之内。
     *
     * @注意： 如果是 bitmap.compress(Bitmap.CompressFormat.PNG, quality, baos);
     * 这样的png格式，quality就没有作用了，bytes.length不会变化，因为png图片是无损的，不能进行压缩。
     *
     * 还有一种格式，CompressFormat.WEBP格式，该格式是google自己推出来一个图片格式
     */
    public static byte[] compressQuality(Bitmap bitmap, int quality){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    /**
     * TODO 2.采样率压缩,bitmap所占内存根据inSampleSize变小
     * @param context
     * @param res
     * @param inSampleSize 传入2，就压缩为原来的 1/2
     * 设置inSampleSize的值(int类型)后，假如设为2，则宽和高都为原来的1/2，宽高都减少了，自然内存也降低了。
     *
     * @注意 我上面的代码没用过options.inJustDecodeBounds = true;
     * 因为我是固定来取样的数据，为什么这个压缩方法叫采样率压缩，
     * 是因为配合inJustDecodeBounds，先获取图片的宽、高【这个过程就是取样】，
     * 然后通过获取的宽高，动态的设置inSampleSize的值。
     *
     * 当inJustDecodeBounds设置为true的时候，BitmapFactory通过decodeResource或者decodeFile解码图片时，
     * 将会返回空(null)的Bitmap对象，这样可以避免Bitmap的内存分配，但是它可以返回Bitmap的宽度、高度以及MimeType。
     *
     */
    public static Bitmap compressRate(Context context,int res, int inSampleSize){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), res, options);
        return bm;
    }

    /**
     * TODO 3.缩放法压缩（Matrix矩阵），bitmap所占内存根据matrix缩放比例变小,比采样率压缩更小
     * @param context
     * @param res
     * @param sx
     * @param sy
     * @注意 如果传入的sx和sy大于1，也就是放大操作可能会出现内存不足异常
     * sx和sy不同的话，可能造成图片被拉伸或挤压，这也是它和采样率压缩不同的地方
     */
    public static Bitmap compressMatrix(Context context,int res,float sx,float sy){
        Bitmap bit = setRGB_565(context, res);
        Matrix matrix = new Matrix();
        matrix.setScale(sx, sy);
        return Bitmap.createBitmap(bit, 0, 0, bit.getWidth(), bit.getHeight(), matrix, true);
    }
    public static Bitmap compressMatrix(String imgPath,float sx,float sy){
        Bitmap bit = setRGB_565(imgPath);
        Matrix matrix = new Matrix();
        matrix.setScale(sx, sy);
        return Bitmap.createBitmap(bit, 0, 0, bit.getWidth(), bit.getHeight(), matrix, true);
    }

    /**
     * TODO 4.把图片设置为RGB_565，相比argb_8888减少了一半的内存开销，且长度和宽度不变
     * @param context
     * @param res
     */
    public static Bitmap setRGB_565(Context context,int res){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeResource(context.getResources(), res, options);
    }
    public static Bitmap setRGB_565(String pathName){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(pathName, options);
    }


    /**
     * TODO 5.压缩成用户希望的宽高
     * 首先把图片设置为RGB_565来减小大小
     * @param context
     * @param res
     * @param witdh
     * @param height
     * @注意 如果希望的宽高和原图差太多，图片会很不清晰
     * 如果宽高和原图宽高比不同，图片还会被挤压和拉伸
     */
    public static Bitmap compressWH(Context context,int res,int witdh,int height){
       return Bitmap.createScaledBitmap(setRGB_565(context, res), witdh, height, true);
    }
    public static Bitmap compressWH(String pathName,int witdh,int height){
        return Bitmap.createScaledBitmap(setRGB_565(pathName), witdh, height, true);
    }
    public static File compressImageReturnFile(String filePath, String targetPath, int quality)  {
        Bitmap bm = setRGB_565(filePath);
        File outputFile=new File(targetPath);
        try {
            if (!outputFile.exists()) {
                outputFile.getParentFile().mkdirs();
            }else{
                outputFile.delete();
            }
            FileOutputStream out = new FileOutputStream(outputFile);
            bm.compress(Bitmap.CompressFormat.JPEG, quality, out);
        }catch (Exception e){}
        return outputFile;
    }

    public static InputStream bitmapToInputStream(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        InputStream in = new ByteArrayInputStream(baos.toByteArray());
        return in;
    }

}
