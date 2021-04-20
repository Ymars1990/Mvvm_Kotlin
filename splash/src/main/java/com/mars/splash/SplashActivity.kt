package com.mars.splash

import android.os.Bundle
import android.os.Message
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.mars.network.base.BaseReponseModel
import com.mars.splash.SplashConstant.Companion.AD_DELAYMILLIS
import com.mars.splash.bean.SplashAdBean
import com.mars.splash.databinding.ActivitySplashBinding
import com.mars.splash.model.SplashViewModel
import com.ymars.poj.base.state.DataState
import com.ymars.poj.base.ui.LifecycerActivity
import com.ymars.poj.component.ArouterConstant
import com.ymars.poj.comutils.LogTools
import kotlinx.android.synthetic.main.activity_splash.*
import kotlin.random.Random

@Route(path = ArouterConstant.Splash_MAIN)
class SplashActivity : LifecycerActivity<SplashViewModel, ActivitySplashBinding>() {
    override fun setLayout(savedInstanceState: Bundle?): Int {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash
    }

    override fun initParams() {
        super.initParams()
        vb = DataBindingUtil.setContentView(this, R.layout.activity_splash)
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
            R.id.adDuraTv ->
            {
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