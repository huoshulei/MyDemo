package com.example.huo.mydemo.component;

import android.content.Context;

import com.example.huo.mydemo.api.GanKApi;
import com.example.huo.mydemo.api.GanKApiService;
import com.example.huo.mydemo.module.AppModule;
import com.example.huo.mydemo.module.GanKApiModule;

import dagger.Component;

/**
 * 项目名称:  MyDemo
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/19 10:25
 * 修改人:    ICOGN
 * 修改时间:  2016/10/19 10:25
 * 备注:
 * 版本:
 */
@Component(modules = {AppModule.class, GanKApiModule.class})
public interface AppComponent {
    Context getContext();
    GanKApiService getGanKApiService();
}
