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
abstract class LifecycerActivity<VM : BaseViewModel<*>, VB : ViewBinding> : AppCompatActivity() {
    lateinit var mCtx: Context
    lateinit var vm: VM
    lateinit var vb: VB
    val TAG: String by lazy {
        this.javaClass.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCtx = this
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        initParams()
        setContentView(setLayout(savedInstanceState))
        vm = ViewModelProvider.AndroidViewModelFactory(application)
            .create(ClassReflactUtils.getClass(this))
        vm.loadState.observe(this, observer)
        lifecycle.addObserver(MyLifecycleObserver(TAG))
        doWork()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler!!.removeCallbacksAndMessages(null)
    }

    abstract fun setLayout(savedInstanceState: Bundle?): Int
    abstract fun doWork()
    abstract fun initParams()

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

    val  handler:MyHandler by lazy{
        MyHandler(this)
    }
    companion object {
        class MyHandler(activity:LifecycerActivity<*,*>) : Handler() {
            private val mActivity: WeakReference<LifecycerActivity<*,*>> =
                WeakReference(activity)

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
//                LogTools.i(mActivity!!.TAG,"MyHandler handleMessage")
            }
        }
    }
}
