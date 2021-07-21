package com.ymars.poj.mvvm_kotlin.repository

import com.ymars.network.base.BaseApiService
import com.ymars.network.base.BaseReponse
import com.mars.splash.bean.SplashAdBean
import com.ymars.poj.mvvm_kotlin.bean.TabBean
import io.reactivex.Observable
import retrofit2.http.GET


/**
 * @author Mars
 * 请求
 */
interface MainService: BaseApiService {
    /**
     * 获取启动页广告
     */
    @GET("/v1/vertical/vertical")
    suspend fun getTestData(): BaseReponse<SplashAdBean>

}