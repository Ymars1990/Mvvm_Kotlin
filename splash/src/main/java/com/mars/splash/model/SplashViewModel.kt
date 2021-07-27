package com.mars.splash.model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ymars.network.NetConstant
import com.ymars.network.RetrofitManagerFactory
import com.mars.splash.bean.SplashAdBean
import com.mars.splash.repository.SplashService
import com.ymars.poj.base.model.BaseViewModel

class SplashViewModel(application: Application) : BaseViewModel<SplashService>(application) {

    val adInfor: MutableLiveData<SplashAdBean> = MutableLiveData()


    override fun initApiservice(): SplashService {
        return RetrofitManagerFactory.instance.create(SplashService::class.java)
    }
}