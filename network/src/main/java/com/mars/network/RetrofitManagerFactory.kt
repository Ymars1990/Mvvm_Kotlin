package com.mars.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mars.network.interceptor.LoggingInterceptor
import com.mars.network.gsonfactory.*
import com.mars.network.interceptor.FilterUrlInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Mars
 * 请求管理工厂
 * 负责发送和接收数据
 */
class RetrofitManagerFactory private constructor() {

    private val retrofit: Retrofit

    fun <T> create(clz: Class<T>): T {
        return retrofit.create(clz)
    }

    companion object {
        val instance by lazy {
            RetrofitManagerFactory()
        }
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(NetConstant.BASE_SERVER_URL)
//            .addConverterFactory(BaseConverterFactory.create())
//            .addConverterFactory(initGsonConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(initOkHttpClient())
            .build();
    }


    private fun initOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(LoggingInterceptor())
            .addInterceptor(FilterUrlInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(initCommonInterceptor())
            .followRedirects(false)
            .followSslRedirects(true)
//            .cookieJar(new CookieManger(mCtx))
            .build()
    }

    private fun initCommonInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json;charset=UTF-8")
                .addHeader("Accept", "application/json;charset=UTF-8")
                .addHeader("Accept-Encoding", "")
                .build()

            chain.proceed(request)
        }
    }

}



