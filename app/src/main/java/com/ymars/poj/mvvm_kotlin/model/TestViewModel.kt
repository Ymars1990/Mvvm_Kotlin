package com.ymars.poj.mvvm_kotlin.model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mars.network.RetrofitManagerFactory
import com.ymars.poj.base.model.BaseViewModel
import com.ymars.poj.mvvm_kotlin.repository.MainService

/**
 */
class TestViewModel(application: Application) : BaseViewModel<MainService>(application) {
    val mTxt: MutableLiveData<String> = MutableLiveData<String>("首页")
    override fun initApiservice(): MainService {
        return RetrofitManagerFactory.instance.create(MainService::class.java)
    }

}