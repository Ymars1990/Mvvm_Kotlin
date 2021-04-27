package com.ymars.poj.mvvm_kotlin.ui

import android.os.Bundle
import android.os.Message
import androidx.lifecycle.Observer
import com.ymars.poj.base.ui.LifrcyclerFragment
import com.ymars.poj.comutils.LogTools
import com.ymars.poj.mvvm_kotlin.R
import com.ymars.poj.mvvm_kotlin.databinding.FragmentTestBinding
import com.ymars.poj.mvvm_kotlin.model.TestViewModel

class TestFragment() :
    LifrcyclerFragment<TestViewModel, FragmentTestBinding>() {

    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.fragment_test
    }

    override fun doWork() {
        vm.mTxt.observe(this, txtObserver)
    }

    override fun handlerMsg(msg: Message) {

    }

    private val txtObserver by lazy {
        Observer<String> {
            it?.let {
                LogTools.i(TAG, vm.mTxt.value)
                vb.testTv.text = vm.mTxt.value
            }
        }
    }
}

