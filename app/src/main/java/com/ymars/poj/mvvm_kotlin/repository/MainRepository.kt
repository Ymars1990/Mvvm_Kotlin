package com.ymars.poj.mvvm_kotlin.repository

import androidx.lifecycle.MutableLiveData
import com.mars.network.base.BaseObserver
import com.mars.network.execute
import com.mars.splash.bean.SplashAdBean
import com.ymars.poj.base.state.DataState
import com.ymars.poj.mvvm_kotlin.bean.TabBean

open class MainRepository(private val loadState: MutableLiveData<DataState>) : ApiRepository() {
    fun getTabBean(liveData: MutableLiveData<ArrayList<TabBean>>) {
//        apiService.getTabBean().execute(BaseObserver(liveData, loadState, this))
    }
}
