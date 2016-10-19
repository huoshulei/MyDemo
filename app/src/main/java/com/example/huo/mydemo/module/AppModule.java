package com.example.huo.mydemo.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * 项目名称:  MyDemo
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/19 10:26
 * 修改人:    ICOGN
 * 修改时间:  2016/10/19 10:26
 * 备注:
 * 版本:
 */
@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContent() {
        return context;
    }
}
