package com.ymars.poj.mvvm_kotlin.model

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mars.splash.bean.SplashAdBean
import com.ymars.poj.mvvm_kotlin.repository.MainRepository
import com.ymars.poj.base.model.BaseViewModel
import com.ymars.poj.comutils.LogTools
import com.ymars.poj.mvvm_kotlin.R
import com.ymars.poj.mvvm_kotlin.bean.TabBean

/**
 */
class TestViewModel(application: Application) : BaseViewModel<MainRepository>(application) {
     val mTxt: MutableLiveData<String> = MutableLiveData<String>("首页")

}