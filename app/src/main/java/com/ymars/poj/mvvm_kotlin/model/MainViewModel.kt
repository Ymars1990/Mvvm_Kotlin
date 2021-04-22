package com.ymars.poj.mvvm_kotlin.model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mars.splash.bean.SplashAdBean
import com.ymars.poj.mvvm_kotlin.repository.MainRepository
import com.ymars.poj.base.model.BaseViewModel
import com.ymars.poj.mvvm_kotlin.R
import com.ymars.poj.mvvm_kotlin.bean.TabBean

/**
 * [ TabBean("首页", R.mipmap.ic_launcher,"")
,TabBean("广场", R.mipmap.ic_launcher,"")
,TabBean("我的", R.mipmap.ic_launcher,"")]
 */
class MainViewModel(application: Application) : BaseViewModel<MainRepository>(application) {
    val tabBeans: MutableLiveData<ArrayList<TabBean>>  by lazy {
        MutableLiveData<ArrayList<TabBean>>().also {
            it.value = arrayListOf(TabBean("首页", R.mipmap.ic_launcher,"",1,0)
                ,TabBean("广场", R.mipmap.ic_launcher,"",0,1)
                ,TabBean("我的", R.mipmap.ic_launcher,"",0,2))
        }
    }
     fun getTabBean(){
        mRespository.getTabBean(tabBeans)
    }
}