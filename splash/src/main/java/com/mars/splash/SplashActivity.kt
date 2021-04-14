package com.mars.splash

import android.os.Bundle
import com.mars.splash.databinding.ActivitySplashBinding
import com.mars.splash.model.SplashViewModel
import com.ymars.poj.base.ui.BaseActivity

class SplashActivity: BaseActivity<SplashViewModel, ActivitySplashBinding>() {
    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.activity_splash
    }

    override fun doWork() {
    }

    override fun initParams() {
    }
}