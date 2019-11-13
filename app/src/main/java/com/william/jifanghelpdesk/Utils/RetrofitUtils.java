package com.william.jifanghelpdesk.Utils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.william.jifanghelpdesk.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit封装
 */
public class RetrofitUtils {
    private static final String TAG = "RetrofitUtils";
    private static ApiUrl mApiUrl;

    /**
     * 单例模式
     */
    public static ApiUrl getApiUrl() {
        if (mApiUrl == null) {
            synchronized (RetrofitUtils.class) {
                if (mApiUrl == null) {
                    mApiUrl = new RetrofitUtils().getRetrofit();
                }
            }
        }
        return mApiUrl;
    }

    private RetrofitUtils() {
    }

    /**
     * 获取接口
     */
    public ApiUrl getRetrofit() {
        ApiUrl apiUrl = initRetrofit(initOkHttp()).create(ApiUrl.class);
        return apiUrl;
    }

    /**
     * 初始化Retrofit
     */
    private Retrofit initRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(Constans.Base_Url)//指定base url
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// 支持RxJava平台
                .addConverterFactory(GsonConverterFactory.create())// 添加GSON解析：返回数据转换成GSON类型
                .build();
    }

    /**
     * 初始化okhttp
     */
    private OkHttpClient initOkHttp(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        builder.readTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)// 设置读取超时时间
                .connectTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)// 设置请求超时时间
                .writeTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS);// 设置写入超时时间

        if (BuildConfig.DEBUG){
            builder.addInterceptor(new LoggingInterceptor())// 使用Log拦截器
                    .retryOnConnectionFailure(true);// 出现错误时重新连接
        }

        OkHttpClient client = builder.build();

        return client;
    }
}