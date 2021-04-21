package com.mars.splash

import android.os.Build
import android.os.Bundle
import android.os.Message
import android.view.View
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.mars.splash.SplashConstant.Companion.AD_DELAYMILLIS
import com.mars.splash.bean.SplashAdBean
import com.mars.splash.databinding.ActivitySplashBinding
import com.mars.splash.model.SplashViewModel
import com.ymars.poj.base.ui.LifecycerActivity
import com.ymars.poj.component.ArouterConstant
import com.ymars.poj.comutils.LogTools
import kotlin.random.Random

@Route(path = ArouterConstant.Splash_MAIN)
class SplashActivity : LifecycerActivity<SplashViewModel, ActivitySplashBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        hideSysBar()
        super.onCreate(savedInstanceState)
    }
    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.activity_splash
    }

    override fun initParams() {
        super.initParams()
        vb = DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }

    private fun hideSysBar(){
        if (Build.VERSION.SDK_INT >=30) {
            val controller = ViewCompat.getWindowInsetsController(window.decorView)
            controller?.also {
                it.hide(WindowInsetsCompat.Type.statusBars())
                it.hide(WindowInsetsCompat.Type.navigationBars())
            }
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
    override fun doWork() {
        handler.sendEmptyMessage(0x01);
        vm.showAd()
        vm.adInfor.observe(this, observer)
    }

    var duration: Long = 0

    override fun handlerMsg(msg: Message) {
        when (msg.what) {
            0x01 -> {
                vb.adDuraTv.text = String.format("%ss", AD_DELAYMILLIS / 1000 - duration)
                if (duration * 1000 < AD_DELAYMILLIS) {
                    duration++
                    handler.sendEmptyMessageDelayed(0x01, 1000)
                } else {
                    LogTools.i(TAG, "广告结束，跳转主页")
                    ARouter.getInstance().build(ArouterConstant.APP_MAIN).navigation()
                    finish()
                }

            }
        }
    }

    fun click(v: View?) {
        when (v?.id) {
            R.id.adDuraTv -> {
                LogTools.i(TAG, "跳过广告，跳转主页")
                handler.removeCallbacksAndMessages(null)
                ARouter.getInstance().build(ArouterConstant.APP_MAIN).navigation()
                finish()
            }
        }
    }

    private val observer by lazy {
        Observer<SplashAdBean> {
            it?.let {
                var radom: Int = Random.nextInt(it.vertical.size)
                LogTools.i(TAG, "" + radom)
                LogTools.i(TAG, "" + it.vertical.size)
                LogTools.i(TAG, it.vertical[radom].toString())
                Glide.with(this)
                    .load(it.vertical[radom].wp)
                    .into(vb.adIv)

                LogTools.i(TAG, it.toString())
            }
        }
    }
}