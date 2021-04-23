package com.ymars.poj.base.ui

import android.content.Context
import android.database.DatabaseUtils
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import com.ymars.poj.base.BaseApplication
import com.ymars.poj.base.model.BaseViewModel
import com.ymars.poj.base.state.DataState
import com.ymars.poj.comutils.ClassReflactUtils
import com.ymars.poj.comutils.LogTools
import java.lang.ref.WeakReference

abstract class LifrcyclerFragment<VM : BaseViewModel<*>, VB : ViewBinding> : BaseFragment<VB>() {
    lateinit var vm: VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCtx = context
    }

    override fun initParams(savedInstanceState: Bundle?) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(BaseApplication.get())
            .create(ClassReflactUtils.getClass(this))
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