package com.ymars.poj.base.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ymars.poj.base.model.BaseViewModel
import com.ymars.poj.base.state.DataState
import com.ymars.poj.comutils.ClassReflectUtils
import com.ymars.poj.comutils.LogTools

/**
 * @author Mars
 * 基类Activity
 */
abstract class LifecycerActivity<VM : BaseViewModel<*>, VB : ViewBinding> : BaseActivity() {
    lateinit var vm: VM
    lateinit var vb: VB

    override fun initParams(savedInstanceState: Bundle?) {
        vm = ViewModelProvider.AndroidViewModelFactory(application)
            .create(ClassReflectUtils.getClass(this))
        vm.loadState.observe(this, observer)
        lifecycle.addObserver(MyLifecycleObserver(TAG))
        vb = DataBindingUtil.setContentView(this, setLayout(savedInstanceState))

    }

    private val observer by lazy {
        Observer<DataState> {
            it?.let {

                when (it) {
                    DataState.DataStateType.LOADING -> LogTools.i(TAG, "LOADING")
                    DataState.DataStateType.SUCCESS -> {
                        LogTools.i(TAG, "SUCCESS")
                    }
                    DataState.DataStateType.ERROR -> LogTools.e(TAG, "ERROR")
                    DataState.DataStateType.NETWORK_ERROR -> LogTools.e(TAG, "NETWORK_ERROR")
                    DataState.DataStateType.NULL ->
                        LogTools.e(TAG, "NULL")
                    DataState.DataStateType.EMPTY ->
                        LogTools.e(TAG, "EMPTY")
                }
            }
        }
    }
}
