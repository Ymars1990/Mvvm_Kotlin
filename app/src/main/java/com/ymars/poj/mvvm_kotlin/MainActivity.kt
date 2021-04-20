package com.ymars.poj.mvvm_kotlin

import android.os.Bundle
import android.os.Message
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.ymars.poj.base.ui.LifecycerActivity
import com.ymars.poj.component.ArouterConstant
import com.ymars.poj.mvvm_kotlin.databinding.ActivityMainBinding
import com.ymars.poj.mvvm_kotlin.model.MainViewModel

@Route(path = ArouterConstant.APP_MAIN)
class MainActivity : LifecycerActivity<MainViewModel, ActivityMainBinding>() {
    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }
    override fun initParams() {
    }

    override fun handlerMsg(msg: Message) {
    }

    override fun doWork() {
        vb =  DataBindingUtil.setContentView(this,R.layout.activity_main)
        vb.test.text="测试"
    }
}
