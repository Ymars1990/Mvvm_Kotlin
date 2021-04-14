package com.ymars.poj.mvvm_kotlin

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ymars.poj.base.ui.BaseActivity
import com.ymars.poj.mvvm_kotlin.databinding.ActivityMainBinding
import com.ymars.poj.mvvm_kotlin.model.MainViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }
    override fun initParams() {
    }
    override fun doWork() {
        vb =  DataBindingUtil.setContentView(this,R.layout.activity_main)
        vb.test.text="测试"
    }
}
