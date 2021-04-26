package com.ymars.poj.mvvm_kotlin.ui

import android.os.Bundle
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.ymars.poj.base.ui.LifrcyclerFragment
import com.ymars.poj.comutils.LogTools
import com.ymars.poj.mvvm_kotlin.R
import com.ymars.poj.mvvm_kotlin.databinding.FragmentTestBinding
import com.ymars.poj.mvvm_kotlin.model.MainViewModel
import com.ymars.poj.mvvm_kotlin.model.TestViewModel

class TestFragment() :
    LifrcyclerFragment<TestViewModel, FragmentTestBinding>() {

    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.fragment_test
    }

    override fun doWork() {
        vm.mTxt.observe(this, txtObserver)
        vb.testEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString() != vm.mTxt.value) {
                    vm.mTxt.postValue(p0.toString())
                }
            }

        })
    }

    override fun handlerMsg(msg: Message) {

    }

    private val txtObserver by lazy {
        Observer<String> {
            it?.let {
                LogTools.i(TAG, vm.mTxt.value)
                vb.testTv.text = vm.mTxt.value
                vb.testEt.text = Editable.Factory.getInstance().newEditable(vm.mTxt.value)
            }
        }
    }
}

