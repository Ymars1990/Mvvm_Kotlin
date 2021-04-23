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
class MainViewModel(application: Application) : BaseViewModel<MainRepository>(application) {
    val tabBeans: MutableLiveData<ArrayList<TabBean>> by lazy {
        MutableLiveData<ArrayList<TabBean>>().also {
            it.value = arrayListOf(
                TabBean("首页", R.mipmap.ic_default, "", 1, 0),
                TabBean(
                    "广场",
                    R.mipmap.ic_default,
                    "https://images.5296live.com/upload/202104/20/012e5792125943a692933761554c4f2c.jpeg",
                    0,
                    1
                ),
                TabBean("我的", R.mipmap.ic_default, "", 0, 2)
            )
        }
    }

    fun getTabBean() {
        mRespository.getTabBean(tabBeans)
    }

    var tabSelectPos: MutableLiveData<Int> = MutableLiveData<Int>(0)
    fun onItemClick(v: View, tabBean: TabBean) {
        LogTools.i(TAG, tabBean.toString())
        when (v.id) {
            R.id.tabItemLl -> {
                if (tabSelectPos.value != tabBean.pos) {
                    for (item: TabBean in tabBeans.value!!) {
                        if (item.pos == tabBean.pos) {
                            item.state = 1
                            tabSelectPos.postValue(tabBean.pos)
                        } else {
                            item.state = 0
                        }
                    }
                    tabBeans.postValue(tabBeans.value)
                }else{
                    LogTools.i(TAG,"点击tab 与上次tab相同")
                }
            }
        }
    }
}