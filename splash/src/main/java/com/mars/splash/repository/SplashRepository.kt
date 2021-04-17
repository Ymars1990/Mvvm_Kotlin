package com.mars.splash.repository

import androidx.lifecycle.MutableLiveData
import com.mars.network.base.BaseObserver
import com.mars.network.base.BaseReponseModel
import com.mars.network.execute
import com.mars.splash.bean.SplashAdBean
import com.ymars.poj.base.state.DataState

open class SplashRepository(private val loadState: MutableLiveData<DataState>) : ApiRepository() {

    fun getSplashAd(liveData: MutableLiveData<SplashAdBean>) {
        apiService.getSplashAd().execute(BaseObserver(liveData, loadState, this))
    }
}
