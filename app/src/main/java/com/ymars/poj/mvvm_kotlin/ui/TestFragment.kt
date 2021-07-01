package com.ymars.poj.mvvm_kotlin.ui

import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mars.splash.bean.SplashAdBean
import com.ymars.poj.base.myinterface.OnItemClicker
import com.ymars.poj.base.ui.LifrcyclerFragment
import com.ymars.poj.comutils.LogTools
import com.ymars.poj.mvvm_kotlin.R
import com.ymars.poj.mvvm_kotlin.adapter.TestDataAdapter
import com.ymars.poj.mvvm_kotlin.databinding.FragmentTestBinding
import com.ymars.poj.mvvm_kotlin.model.TestViewModel

class TestFragment :
    LifrcyclerFragment<TestViewModel, FragmentTestBinding>(), OnItemClicker<SplashAdBean.AdBean> {
    var adapter: TestDataAdapter? = null

    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.fragment_test
    }

    override fun doWork() {
        initData()
        vm.getTestData()
        vm.mRvData.observe(this, rvDataObserver)
        vb.testRv.layoutManager = LinearLayoutManager(mCtx)
        vb.testRv.adapter = adapter
    }

    override fun handlerMsg(msg: Message) {

    }

    private val rvDataObserver by lazy {
        Observer<SplashAdBean> {
            it?.let {
                LogTools.i(TAG, it.toString())
                adapter!!.refreshData(it.vertical)
            }
        }
    }

    override fun onItemClick(v: View, data: SplashAdBean.AdBean, pos: Int) {
        LogTools.i(TAG, data.toString())

    }

    private fun initData() {
        adapter = TestDataAdapter(vm, this);
    }
}
