package com.example.huo.mydemo.api;

import com.example.huo.mydemo.bean.GankIoEntity;
import com.example.huo.mydemo.bean.ResultBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 项目名称:  MyDemo
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/19 9:52
 * 修改人:    ICOGN
 * 修改时间:  2016/10/19 9:52
 * 备注:
 * 版本:
 */

public interface GanKApiService {
    /**
     * token刷新token
     *
     * @param token oldToken
     * @return
     */
    @FormUrlEncoded
    @POST("app/token.do")
    Call<String> refreshToken(@Field("token") String token);

    /**
     * @param type     {Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App 可以选其一}
     * @param quantity {0<quantity<=50}
     * @param page     {页码>0}
     */
    @GET("api/data/{type}/{quantity}/{page}")
    Observable<GankIoEntity<List<ResultBean>>> getData(
            @Path("type") String type,
            @Path("quantity") String quantity,
            @Path("page") String page);

    /**
     * @param type     {Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App 可以选其一}
     * @param quantity {0<quantity<=50}
     * @param page     {页码>0}
     */
    @GET("api/{data}/{type}/{quantity}/{page}")
    <T> Observable<GankIoEntity<List<T>>> getData(
            @Path("data") String data,
            @Path("type") String type,
            @Path("quantity") String quantity,
            @Path("page") String page);
}
