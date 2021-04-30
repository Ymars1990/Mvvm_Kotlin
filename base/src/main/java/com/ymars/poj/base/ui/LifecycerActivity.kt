package com.ymars.poj.base.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.mars.network.base.BaseError
import com.ymars.poj.base.model.BaseViewModel
import com.ymars.poj.base.state.DataState
import com.ymars.poj.comutils.ClassReflectUtils
import com.ymars.poj.comutils.LogTools

/**
 * @author Mars
 * 基类Activity
 */
abstract class LifecycerActivity<VM : BaseViewModel<*>,VB:ViewBinding> : BaseActivity<VB>() {
    lateinit var vm: VM

    override fun initParams(savedInstanceState: Bundle?) {
        vm = ViewModelProvider.AndroidViewModelFactory(application)
            .create(ClassReflectUtils.getClass(this))
        vm.loadState.observe(this, observer)
        vm.errorData.observe(this, errorObserve)
        lifecycle.addObserver(MyLifecycleObserver(TAG))
        vb = DataBindingUtil.setContentView(this, setLayout(savedInstanceState))

    }

    private val errorObserve by lazy {
        Observer<BaseError> {
            it?.let {
                LogTools.i(TAG, it.toString())
                handler.sendEmptyMessage(0xE1)
            }
        }
    }

    private val observer by lazy {
        Observer<DataState> {
            it?.let {

                when (it) {
                    DataState.DataStateType.LOADING -> {
                        LogTools.i(TAG, "LOADING")
                    }
                    DataState.DataStateType.SUCCESS -> {
                        LogTools.i(TAG, "SUCCESS")
                    }
                    DataState.DataStateType.ERROR -> {
                        LogTools.e(TAG, "ERROR")
                    }
                    DataState.DataStateType.NETWORK_ERROR -> {
                        LogTools.e(TAG, "NETWORK_ERROR")
                    }
                    DataState.DataStateType.NULL ->
                        LogTools.e(TAG, "NULL")
                    DataState.DataStateType.EMPTY ->
                        LogTools.e(TAG, "EMPTY")
                }
            }
        }
    }
}
