package com.mars.splash

import com.ymars.poj.base.BaseApplication
import com.ymars.poj.comutils.LogTools

class SplashApplication: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        LogTools.i(TAG,"initArouter")
    }

    override fun initModuleApp(application: BaseApplication?) {

    }
}