package com.mars.splash.repository

import com.mars.network.base.BaseReponseModel
import com.mars.splash.bean.SplashAdBean
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
    fun getSplashAd(): Observable<BaseReponseModel<SplashAdBean>>


}