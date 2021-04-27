package com.mars.splash.repository

import com.mars.network.base.BaseApiService
import com.mars.network.base.BaseReponse
import com.mars.splash.bean.SplashAdBean
import retrofit2.http.GET


/**
 * @author Mars
 * 请求
 */
interface SplashService:BaseApiService {


    /**
     * 获取启动页广告
     */
    @GET("/v1/vertical/vertical")
    suspend fun getSplashAd(): BaseReponse<SplashAdBean>
}