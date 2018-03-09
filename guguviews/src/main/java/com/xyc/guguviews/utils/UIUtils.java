package com.xyc.guguviews.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.DecimalFormat;


/**
 * Created by gugu on 2018/1/6.
 */

public class UIUtils {
    /**
     * dp转px
     *
     * @param context
     * @param dip
     * @return
     */
    public static int dipToPx(Context context, int dip) {
        return (int) (dip * getScreenDensity(context) + 0.5f);
    }

    /**
     * float保留2位有效数字
     *
     * @param data
     * @return
     */
    public static String getDoublePoint(float data) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        return decimalFormat.format(data);//format 返回的是字符串
    }

    /**
     * KB转MB
     *
     * @param sizeKb
     * @return
     */
    public static String kbToMb(long sizeKb) {
        float sizeMb = sizeKb / (1024);
        return getDoublePoint(sizeMb);

    }


    /**
     * 获取屏幕像素
     *
     * @param context
     * @return
     */
    public static float getScreenDensity(Context context) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                    .getMetrics(dm);
            return dm.density;
        } catch (Exception e) {
            return DisplayMetrics.DENSITY_DEFAULT;
        }
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context
     * @param sp      （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float sp) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }


    public static String getChangeContent(String content, String tips) {
        if (content == null || content.isEmpty()) {
            return tips;
        }
        return content;
    }
}
