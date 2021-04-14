package com.ymars.poj.base.ui

import android.content.Context
import android.os.Bundle
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
    lateinit var v: VB

    val TAG: String by lazy {
        LogTools.i("Base TAG",this.javaClass.simpleName)
       this.javaClass.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCtx = this
        initParams()
        LogTools.i(TAG,"onCreate")
        setContentView(setLayout(savedInstanceState))
        doWork()
    }

    abstract fun setLayout(savedInstanceState: Bundle?): Int
    abstract fun doWork()
    abstract fun initParams()

}
