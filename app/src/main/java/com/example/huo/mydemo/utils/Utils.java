package com.example.huo.mydemo.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 项目名称:  MyDemo
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/20 15:28
 * 修改人:    ICOGN
 * 修改时间:  2016/10/20 15:28
 * 备注:
 * 版本:
 */

public class Utils {
    public static float getScreenHeight(Context context) {
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
//        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        return height;
    }
}
