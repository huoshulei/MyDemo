package com.example.huo.mydemo.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
