package com.ljb.fire.api;

import com.ljb.fire.app.App;
import com.ljb.fire.utils.NetWorkUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络工具类
 */

public class RetrofitUtil {
    /**
     * 读取超长时间
     */
    public static final int READ_TIME_OUT = 7676;
    /**
     * 连接超长时间
     */
    public static final int CONNECT_TIME_OUT = 7676;
    /*************************缓存设置*********************/
/*
   1. noCache 不使用缓存，全部走网络

    2. noStore 不使用缓存，也不存储缓存

    3. onlyIfCached 只使用缓存

    4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合

    5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言

    6. minFresh 设置有效时间，依旧如上

    7. FORCE_NETWORK 只走网络

    8. FORCE_CACHE 只走缓存*/
    /**
     * 缓存有效时间为两天
     */
    public static final long CACHE_STATE_SEC = 60 * 60 * 24 * 2;
    private static RetrofitUtil sRetrofitUtil;
    private OkHttpClient mOkHttpClient;
    private NetWorkCacheIntercepter mNetWorkCacheIntercepter = new NetWorkCacheIntercepter();
    private HttpLoggingInterceptor mHttpLoggingInterceptor;
    private Interceptor mHeaderIntercept;
    private Retrofit mRetrofit;

    private RetrofitUtil() {
        initLoggingInterceptor();
        initHeaderInterceptor();
        mOkHttpClient = new OkHttpClient.Builder()//
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)//
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)//
                .addInterceptor(mNetWorkCacheIntercepter)//
                .addNetworkInterceptor(mNetWorkCacheIntercepter)//
                .addInterceptor(mHeaderIntercept)//
                .addInterceptor(mHttpLoggingInterceptor)//
                .build();

        mRetrofit = new Retrofit.Builder()//
                .client(mOkHttpClient)//
//                .baseUrl()//
                .addConverterFactory(GsonConverterFactory.create())//
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//
                .build();
    }


    private void initHeaderInterceptor() {
        mHeaderIntercept = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()//
                        .addHeader("Content-Type", "application/json")//
                        .build();
                return chain.proceed(request);
            }
        };
    }

    private void initLoggingInterceptor() {
        mHttpLoggingInterceptor = new HttpLoggingInterceptor();
        mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    public static RetrofitUtil getInstance() {
        if (sRetrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                if (sRetrofitUtil == null) {
                    sRetrofitUtil = new RetrofitUtil();

                }
            }
        }
        return sRetrofitUtil;
    }

    static class NetWorkCacheIntercepter implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            if (NetWorkUtil.isNetConnect(App.getAppContext())) {
                //有网络直接走获取新数据
                return response.newBuilder()//
                        .header("Cache-Control", "public,max-age=" + 0)//
                        .removeHeader("Pragma")//
                        .build();

            } else {//没有网络，只走缓存,缓存时间为2周
                return response.newBuilder()//
                        .header("Cache-Control", "public,only-if-cache,max-state=" + CACHE_STATE_SEC)//
                        .removeHeader("Pragma")//
                        .build();
            }

        }
    }
}
