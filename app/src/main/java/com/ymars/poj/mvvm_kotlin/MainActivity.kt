package com.ymars.poj.mvvm_kotlin

import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.mars.splash.bean.SplashAdBean
import com.ymars.poj.base.ui.LifecycerActivity
import com.ymars.poj.component.ArouterConstant
import com.ymars.poj.comutils.LogTools
import com.ymars.poj.mvvm_kotlin.adapter.MainTabAdapter
import com.ymars.poj.mvvm_kotlin.bean.TabBean
import com.ymars.poj.mvvm_kotlin.databinding.ActivityMainBinding
import com.ymars.poj.mvvm_kotlin.model.MainViewModel

@Route(path = ArouterConstant.APP_MAIN)
class MainActivity : LifecycerActivity<MainViewModel, ActivityMainBinding>() {
    var adapter: MainTabAdapter? = null
    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun initParams(savedInstanceState: Bundle?) {
        super.initParams(savedInstanceState)
        vm.tabBeans.observe(this, observer)
        vm.tabSelectPos.observe(this, posObserver)
        val layoutManager = GridLayoutManager(this, vm.tabBeans.value!!.size)
        vb.tabRv.layoutManager = layoutManager
    }

    override fun handlerMsg(msg: Message) {
    }

    override fun doWork() {
        vm.getTabBean()
        adapter = MainTabAdapter(vm)
        vb.tabRv.adapter = adapter

    }

    private val observer by lazy {
        Observer<ArrayList<TabBean>> {
            it?.let {
                adapter!!.refreshData(it)
            }
        }
    }
    private val posObserver by lazy {
        Observer<Int> {
            it?.let {
                LogTools.i(TAG, vm.tabBeans.value?.get(it).toString())
                vb.testTv.text = vm.tabBeans.value?.get(vm.tabSelectPos.value!!)?.txt

            }
        }
    }
}
