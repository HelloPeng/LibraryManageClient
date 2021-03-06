package com.pansoft.lvzp.librarymanageclient.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * Created by lv_zhp on 2018/5/1.
 * 类描述：利用RenderScript进行图片的高斯模糊（该方案兼容最低版本API17）
 */
public class RenderScriptBlur {

    private static final float BITMAP_SCALE = 0.4f;
    private static final int BLUR_RADIUS = 7;

    public static Bitmap blur(Context context, Bitmap bitmap) {
        return blur(context, bitmap, BITMAP_SCALE, BLUR_RADIUS);
    }

    public static Bitmap blur(Context context, Bitmap bitmap, float bitmap_scale) {
        return blur(context, bitmap, bitmap_scale, BLUR_RADIUS);
    }

    public static Bitmap blur(Context context, Bitmap bitmap, int blur_radius) {
        return blur(context, bitmap, BITMAP_SCALE, blur_radius);
    }

    public static Bitmap blur(Context context, Bitmap bitmap, float bitmap_scale, int blur_radius) {
        //先对图片进行压缩然后再blur
        Bitmap inputBitmap = Bitmap.createScaledBitmap(bitmap, Math.round(bitmap.getWidth() * bitmap_scale),
                Math.round(bitmap.getHeight() * bitmap_scale), false);
        //创建空的Bitmap用于输出
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
        //①、初始化Renderscript
        RenderScript rs = RenderScript.create(context);
        //②、Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        //③、native层分配内存空间
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        //④、设置blur的半径然后进行blur
        theIntrinsic.setRadius(blur_radius);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        //⑤、拷贝blur后的数据到java缓冲区中
        tmpOut.copyTo(outputBitmap);
        //⑥、销毁Renderscript
        rs.destroy();
        bitmap.recycle();

        return outputBitmap;
    }


}
