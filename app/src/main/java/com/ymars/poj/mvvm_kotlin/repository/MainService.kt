package com.ymars.poj.mvvm_kotlin.repository

import com.mars.splash.bean.SplashAdBean
import com.ymars.poj.mvvm_kotlin.bean.TabBean
import io.reactivex.Observable
import retrofit2.http.GET


/**
 * @author Mars
 * 请求
 */
interface ApiService {
    /**
     * 获取启动页广告
     */
    @GET("/v1/vertical/vertical")
    fun getTabBean(): Observable<ArrayList<TabBean>>

}