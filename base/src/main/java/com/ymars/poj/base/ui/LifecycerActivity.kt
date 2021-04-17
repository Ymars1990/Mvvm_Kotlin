package com.ymars.poj.base.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import com.ymars.poj.base.model.BaseViewModel
import com.ymars.poj.base.state.DataState
import com.ymars.poj.comutils.ClassReflactUtils
import com.ymars.poj.comutils.LogTools
import java.lang.ref.WeakReference

/**
 * @author Mars
 * 基类Activity
 */
abstract class LifecycerActivity<VM : BaseViewModel<*>, VB : ViewBinding> : BaseActivity() {
    lateinit var vm: VM
    lateinit var vb: VB

    override fun initParams() {
        vm = ViewModelProvider.AndroidViewModelFactory(application)
            .create(ClassReflactUtils.getClass(this))
        vm.loadState.observe(this, observer)
        lifecycle.addObserver(MyLifecycleObserver(TAG))
    }

    abstract fun updateUi()

    private val observer by lazy {
        Observer<DataState> {
            it?.let {

                when (it) {
                    DataState.DataStateType.LOADING -> LogTools.i(TAG,"LOADING")
                    DataState.DataStateType.SUCCESS ->{
                        LogTools.i(TAG,"SUCCESS")
                        updateUi()
                    }
                    DataState.DataStateType.ERROR -> LogTools.e(TAG,"ERROR")
                    DataState.DataStateType.NETWORK_ERROR ->LogTools.e(TAG,"NETWORK_ERROR")
                    DataState.DataStateType.NULL ->
                        LogTools.e(TAG,"NULL")
                    DataState.DataStateType.EMPTY ->
                        LogTools.e(TAG,"EMPTY")
                }
            }
        }
    }
}
