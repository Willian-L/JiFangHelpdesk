package com.william.jifanghelpdesk.utils.http;

import com.william.jifanghelpdesk.BuildConfig;
import com.william.jifanghelpdesk.utils.log.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * OkHttp封装
 */
public class OkHttpUtils {
    private static final String TAG = "OkHttpUtils";

    /**
     * 单例模式
     */
    private volatile static OkHttpUtils okHttpUtils;
    private OkHttpUtils(){
    }
    public static OkHttpUtils getOkHttpUtils() {

        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    /**
     * 初始化okhttp
     */
    public static OkHttpClient initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        builder.readTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)// 设置读取超时时间
                .connectTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)// 设置请求超时时间
                .writeTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS);// 设置写入超时时间

        if (BuildConfig.DEBUG) {
            builder.retryOnConnectionFailure(true)// 出现错误时重新连接
            .addInterceptor(new LoggingInterceptor());// 使用Log拦截器
        }

        OkHttpClient client = builder.build();

        return client;
    }
}
