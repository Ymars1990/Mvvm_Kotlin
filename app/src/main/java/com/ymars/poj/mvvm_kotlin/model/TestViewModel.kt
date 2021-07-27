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


    override fun initApiservice(): MainService {
        return RetrofitManagerFactory.instance.create(MainService::class.java)
    }
}