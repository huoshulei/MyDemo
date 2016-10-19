package com.example.huo.mydemo.module;

import com.example.huo.mydemo.api.GanKApi;
import com.example.huo.mydemo.api.GanKApiService;

import dagger.Module;
import dagger.Provides;

/**
 * 项目名称:  MyDemo
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/19 10:27
 * 修改人:    ICOGN
 * 修改时间:  2016/10/19 10:27
 * 备注:
 * 版本:
 */
@Module
public class GanKApiModule {
    @Provides
    protected GanKApiService provideGanKApiService() {
        return GanKApi.API.getService();
    }
}
