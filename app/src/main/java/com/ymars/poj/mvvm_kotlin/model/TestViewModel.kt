package com.ymars.poj.mvvm_kotlin.model

import android.app.Application
import android.widget.Space
import androidx.lifecycle.MutableLiveData
import com.ymars.network.NetConstant
import com.ymars.network.RetrofitManagerFactory
import com.mars.splash.bean.SplashAdBean
import com.ymars.poj.base.model.BaseViewModel
import com.ymars.poj.mvvm_kotlin.repository.MainService

/**
 */
class TestViewModel(application: Application) : BaseViewModel<MainService>(application) {
    val mRvData: MutableLiveData<SplashAdBean> = MutableLiveData<SplashAdBean>()
    fun getTestData(page:Int) {
        launch(
            { apiService.getTestData() },
            mRvData,
            NetConstant.BASE_SERVER_URL.plus("/v1/vertical/vertical?limit=4&first=$page")
        )
    }

    override fun initApiservice(): MainService {
        return RetrofitManagerFactory.instance.create(MainService::class.java)
    }
}