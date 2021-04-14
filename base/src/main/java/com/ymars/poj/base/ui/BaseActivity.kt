package com.ymars.poj.base.ui

import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.ymars.poj.base.model.BaseViewModel
import com.ymars.poj.comutils.LogTools

/**
 * @author Mars
 * 基类Activity
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity() {
    lateinit var mCtx: Context
    lateinit var vm: VM
    lateinit var vb: VB
    val TAG: String by lazy {
        LogTools.i("Base TAG", "TAG is init")
        this.javaClass.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCtx = this
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        initParams()
        setContentView(setLayout(savedInstanceState))
        lifecycle.addObserver(MyLifecycleObserver(TAG))
        doWork()
    }

    abstract fun setLayout(savedInstanceState: Bundle?): Int
    abstract fun doWork()
    abstract fun initParams()

}
