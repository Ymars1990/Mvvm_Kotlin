package com.mars.splash.model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mars.network.NetConstant
import com.mars.network.RetrofitManagerFactory
import com.mars.splash.bean.SplashAdBean
import com.mars.splash.repository.SplashService
import com.ymars.poj.base.model.BaseViewModel

class SplashViewModel(application: Application) : BaseViewModel<SplashService>(application) {

    val adInfor: MutableLiveData<SplashAdBean> = MutableLiveData()
    fun showAd() {
        launch(
            { apiService.getSplashAd() },
            adInfor,
            NetConstant.BASE_SERVER_URL.plus("/v1/vertical/vertical")
        )
    }

    override fun initApiservice(): SplashService {
        return RetrofitManagerFactory.instance.create(SplashService::class.java)
    }
}