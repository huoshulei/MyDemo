package com.example.huo.mydemo;


import android.widget.Toast;

import com.example.huo.mydemo.utils.logger.Logger;

import java.lang.reflect.InvocationTargetException;


/**
 * 项目名称:  MyDemo
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/20 9:23
 * 修改人:    ICOGN
 * 修改时间:  2016/10/20 9:23
 * 备注:
 * 版本:
 */

public final class App {
    public static final GanKApplication application;

    static {
        GanKApplication app = null;
        try {
            app = (GanKApplication) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null);
            if (app == null) throw new IllegalStateException("application 初始化必须在主线程");
        } catch (Exception e) {
            Logger.d("App static initializer:初始化失败 from GanKApplication" + e.getMessage());
            try {
                app = (GanKApplication) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null);
            } catch (Exception e1) {
                Logger.d("App static initializer:初始化失败from ActivityThread" + e.getMessage());
            }
        } finally {
            application = app;
        }
    }

    public static void toast(String msg) {
        Toast.makeText(application, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toast(int msgId) {
        Toast.makeText(application, msgId, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(String mag) {
        Toast.makeText(application, mag, Toast.LENGTH_LONG).show();
    }
}
