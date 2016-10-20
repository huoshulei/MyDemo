package com.example.huo.mydemo.api;

import com.example.huo.mydemo.BuildConfig;
import com.example.huo.mydemo.bean.User;
import com.example.huo.mydemo.utils.interceptor.HttpLoggingInterceptor;
import com.example.huo.mydemo.utils.interceptor.HttpLoggingInterceptor.Level;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 项目名称:  MyDemo
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/19 9:50
 * 修改人:    ICOGN
 * 修改时间:  2016/10/19 9:50
 * 备注:
 * 版本:
 */

public enum GanKApi {
    API;
    private GanKApiService service;
    private static final String BASE_URL = "http://gank.io/";

    GanKApi() {
        service = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor()//日志拦截器
                                .setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE))
                        .retryOnConnectionFailure(true)//断线重连
                        .connectTimeout(10, TimeUnit.SECONDS)//超时时间
                        .addNetworkInterceptor(tokenInterceptor)//token拦截器
                        .authenticator(authenticator)//token刷新
                        .build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//rxjava 支持
                .addConverterFactory(GsonConverterFactory.create())//json支持
                .build()
                .create(GanKApiService.class);//创建动态代理
    }

    public GanKApiService getService() {
        return service;
    }

    /**
     * token拦截
     */
    Interceptor   tokenInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (User.token == null || alreadyHasAuthorizationHeader(request))
                return chain.proceed(request);
            Request authorised = request.newBuilder()
                    .header("Authorization", User.token)
                    .build();
            return chain.proceed(authorised);
        }
    };
    /**
     * 刷新token
     */
    Authenticator authenticator    = new Authenticator() {
        /**
         * Returns a request that includes a credential to satisfy an authentication challenge in {@code
         * response}. Returns null if the challenge cannot be satisfied.
         *
         * @param route
         * @param response
         */
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
            User.token = refreshToken(User.token).execute().body();
            return response.request().newBuilder()
                    .addHeader("Authorization", User.token)
                    .build();
        }
    };

    /**
     * @param request
     * @return Authorization 是否为空
     */
    boolean alreadyHasAuthorizationHeader(Request request) {
        String header = request.header("Authorization");
        return header != null && !header.isEmpty();
    }

    private Call<String> refreshToken(String token) {
        return service.refreshToken(token);
    }
}
