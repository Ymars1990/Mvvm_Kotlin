package com.ymars.news.repository

import com.ymars.network.base.BaseApiService
import com.ymars.network.base.BaseReponse
import com.ymars.news.bean.NewsBannerBean
import retrofit2.http.GET


/**
 * @author Mars
 * 请求
 */
interface NewsService : BaseApiService {

    /**
     * 获取轮播
     */
    @GET("/banner/json")
    suspend fun getBanner(): BaseReponse<ArrayList<NewsBannerBean>>
}