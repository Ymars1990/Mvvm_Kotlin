package com.mars.splash

import android.os.Bundle
import android.os.Message
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.mars.splash.databinding.ActivitySplashBinding
import com.mars.splash.model.SplashViewModel
import com.ymars.poj.base.ui.LifecycerActivity
import com.ymars.poj.comutils.LogTools
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : LifecycerActivity<SplashViewModel, ActivitySplashBinding>() {
    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.activity_splash
    }
    override fun initParams() {
        super.initParams()
        vb = DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }
    override fun doWork() {
        vm.showAd()
    }



    override fun handlerMsg(msg: Message) {

    }

    override fun updateUi() {

        Glide.with(mCtx)
            .load("http://t-images.5296live.com/upload/202103/6/4850791af6894ba8a44e84ddb35f7f0e.png")
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(adIv)
    }
}