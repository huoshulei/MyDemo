package com.example.huo.mydemo;

import android.app.Application;
import android.content.Context;

import com.example.huo.mydemo.component.AppComponent;
import com.example.huo.mydemo.component.DaggerAppComponent;
import com.example.huo.mydemo.module.AppModule;
import com.example.huo.mydemo.module.GanKApiModule;
import com.example.huo.mydemo.utils.logger.LogLevel;
import com.example.huo.mydemo.utils.logger.Logger;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * 项目名称:  MyDemo
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/19 10:09
 * 修改人:    ICOGN
 * 修改时间:  2016/10/19 10:09
 * 备注:
 * 版本:
 */

public class GanKApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
        initLogger();
        initComponent();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    /**
     * Component
     */
    private void initComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .ganKApiModule(new GanKApiModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


    /**
     * 初始化日志信息
     */
    private void initLogger() {
        Logger.init("干货")
                .methodCount(3)
                .hideThreadInfo()
                .logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE)
                .methodOffset(2);
    }

    /**
     * 初始化ImageLoader
     */
    private void initImageLoader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
//默认图片 加载中图片 错误图片 圆角
//                .showImageForEmptyUri(R.mipmap.ic_avatar)
//                .showImageOnLoading(R.mipmap.ic_avatar)
//                .showImageOnFail(R.mipmap.ic_avatar)
//                .displayer(new RoundedBitmapDisplayer(getResources().getDimensionPixelOffset(R
//                        .dimen.dp_80)))
                .cacheInMemory(true) // 打开内存缓存
                .cacheOnDisk(true) // 打开硬盘缓存
                .resetViewBeforeLoading(true)// 在ImageView加载前清除它上面之前的图片
                .build();
        // ImageLoader的配置
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder
                (getApplicationContext())
                .memoryCacheSize(5 * 1024 * 1024)// 设置内存缓存为5M
                .defaultDisplayImageOptions(options)// 设置默认的显示选项
                .denyCacheImageMultipleSizesInMemory()//禁止缓存多图
                .threadPriority(3)
                .diskCacheSize(200 * 1024 * 1024)//磁盘缓存大小
                .build();
        // 初始化ImageLoader
        ImageLoader.getInstance().init(config);
    }
}
