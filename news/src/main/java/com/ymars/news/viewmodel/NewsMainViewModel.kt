package com.ymars.news.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ymars.network.RetrofitManagerFactory
import com.ymars.news.bean.NewsBannerBean
import com.ymars.news.repository.NewsService
import com.ymars.poj.base.model.BaseViewModel

class NewsMainViewModel(application: Application) : BaseViewModel<NewsService>(application) {

    val banner: MutableLiveData<ArrayList<NewsBannerBean>> = MutableLiveData()
    fun getBanner() {
        launch(
            { apiService.getBanner() },
            banner,
            "https://www.wanandroid.com/banner/json"
        )
    }

    override fun initApiservice(): NewsService {
        return RetrofitManagerFactory.instance.create(NewsService::class.java)
    }
}