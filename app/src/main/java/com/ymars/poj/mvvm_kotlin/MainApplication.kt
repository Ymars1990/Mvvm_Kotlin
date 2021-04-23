package com.ymars.poj.mvvm_kotlin

import android.util.Log
import com.ymars.poj.base.BaseApplication
import com.ymars.poj.base.Constant
import com.ymars.poj.comutils.LogTools

open class MainApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        LogTools.i(TAG, "initArouter")
    }

    override fun initModuleApp(application: BaseApplication?) {
        /**
         * 初始化模块APP
         */
        /**
         * 初始化模块APP
         */
        for (moduleApp in Constant.moduleApps) {
            try {
                LogTools.i("MainApplication", moduleApp)
                val clazz = Class.forName(moduleApp!!)
                val baseApp = clazz.newInstance() as BaseApplication
                baseApp.initModuleApp(this)
            } catch (e: Exception) {
                LogTools.e("MainApplication", e.message)
                e.printStackTrace()
            }
        }
    }
}