package com.ymars.poj.base.ui

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ymars.poj.base.BaseApplication
import com.ymars.poj.base.model.BaseViewModel
import com.ymars.poj.base.state.DataState
import com.ymars.poj.comutils.ClassReflectUtils
import com.ymars.poj.comutils.LogTools

abstract class LifrcyclerFragment<VM : BaseViewModel<*>, VB : ViewBinding> : BaseFragment<VB>() {
    lateinit var vm: VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCtx = context
    }

    override fun initParams(savedInstanceState: Bundle?) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(BaseApplication.get())
            .create(ClassReflectUtils.getClass(this))
        vm.loadState.observe(viewLifecycleOwner, observer)
        lifecycle.addObserver(MyLifecycleObserver(TAG))

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