package com.ymars.poj.mvvm_kotlin.model

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mars.network.RetrofitManagerFactory
import com.ymars.poj.base.model.BaseViewModel
import com.ymars.poj.comutils.LogTools
import com.ymars.poj.mvvm_kotlin.R
import com.ymars.poj.mvvm_kotlin.bean.TabBean
import com.ymars.poj.mvvm_kotlin.repository.MainService

/**
 */
class MainViewModel(application: Application) : BaseViewModel<MainService>(application) {
    val tabIconOn: MutableLiveData<ArrayList<Int>> by lazy {
        MutableLiveData<ArrayList<Int>>().also {
            it.value = arrayListOf(R.mipmap.ic_news_on, R.mipmap.ic_video_on, R.mipmap.ic_user_on)
        }
    }
    val tabIconOff: MutableLiveData<ArrayList<Int>> by lazy {
        MutableLiveData<ArrayList<Int>>().also {
            it.value = arrayListOf(R.mipmap.ic_news, R.mipmap.ic_video, R.mipmap.ic_user)
        }
    }
    val tabBeans: MutableLiveData<ArrayList<TabBean>> by lazy {
        MutableLiveData<ArrayList<TabBean>>().also {
            it.value = arrayListOf(
                TabBean("热点", R.mipmap.ic_news_on, "", 1, 0),
                TabBean("广场", R.mipmap.ic_video, "", 0, 1),
                TabBean("我的", R.mipmap.ic_user, "", 0, 2)
            )
        }
    }

    fun getTabBean() {
    }

    var tabSelectPos: MutableLiveData<Int> = MutableLiveData<Int>(0)
    fun onItemClick(v: View, tabBean: TabBean) {
        when (v.id) {
            R.id.tabItemLl -> {
                if (tabSelectPos.value != tabBean.pos) {
                    for (item: TabBean in tabBeans.value!!) {
                        if (item.pos == tabBean.pos) {
                            item.state = 1
                            tabSelectPos.postValue(tabBean.pos)
                            tabBeans!!.value!!.get(tabBean.pos).rid =
                                tabIconOn!!.value?.get(tabBean.pos) ?: R.mipmap.ic_default
                        } else {
                            item.state = 0
                            tabBeans!!.value!!.get(item.pos).rid =
                                tabIconOff!!.value?.get(item.pos) ?: R.mipmap.ic_default
                        }
                    }
                    tabBeans.postValue(tabBeans.value)
                } else {
                    LogTools.i(TAG, "点击tab 与上次tab相同")
                }
            }
        }
    }

    override fun initApiservice(): MainService {
        return RetrofitManagerFactory.instance.create(MainService::class.java)
    }
}