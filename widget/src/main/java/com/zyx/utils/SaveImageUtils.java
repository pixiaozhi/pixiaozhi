package com.zyx.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * SaveImageUtils
 * Created by rxy on 2020/1/20.
 */
public class SaveImageUtils {

    public static void saveImageToGallery(Context context, String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;
            int length = http.getContentLength();
            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            if (bm != null) {
                saveImageToGallerys(context, bm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String saveImageToGallerys(Context context, Bitmap bmp) {
        if (bmp == null) {
            //ToastUtils.showShort("保存出错了...");
            return null;
        }
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            //Toast.makeText(context,"保存失败",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            //Toast.makeText(context,"保存失败",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (Exception e) {
            //Toast.makeText(context,"保存失败",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        // 最后通知图库更新
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        //Toast.makeText(context,"保存成功",Toast.LENGTH_SHORT).show();

        return file.getAbsolutePath();
    }

    public static Bitmap getBitmap(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始是先把newOpts.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts); // 此时返回bitmap为null

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 以800*480分辨率为例
        float hh = 800f;  // 这里设置高度为800f
        float ww = 480f;  // 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int scale = 1;  // be=1表示不缩放
        if (w > h && w > ww) {  // 如果宽度大的话根据宽度固定大小缩放
            scale = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) { // 如果高度高的话根据宽度固定大小缩放
            scale = (int) (newOpts.outHeight / hh);
        }
        if (scale <= 0) scale = 1;
        newOpts.inSampleSize = scale; // 设置缩放比例 // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;
    }

    public static Bitmap getBitmap(Context context, Uri uri) {
        try {
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            // 开始是先把newOpts.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true;

            Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, newOpts);

            newOpts.inJustDecodeBounds = false;
            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            // 以800*480分辨率为例
            float hh = 800f;  // 这里设置高度为800f
            float ww = 480f;  // 这里设置宽度为480f
            // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            int scale = 1;  // be=1表示不缩放
            if (w > h && w > ww) {  // 如果宽度大的话根据宽度固定大小缩放
                scale = (int) (newOpts.outWidth / ww);
            } else if (w < h && h > hh) { // 如果高度高的话根据宽度固定大小缩放
                scale = (int) (newOpts.outHeight / hh);
            }
            if (scale <= 0) scale = 1;
            newOpts.inSampleSize = scale; // 设置缩放比例 // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了

            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, newOpts);
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
