package com.mars.splash.model

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.mars.network.base.BaseReponseModel
import com.mars.splash.R
import com.mars.splash.bean.SplashAdBean
import com.mars.splash.repository.SplashRepository
import com.ymars.poj.base.model.BaseViewModel

class SplashViewModel(application: Application) : BaseViewModel<SplashRepository>(application) {

    val adInfor: MutableLiveData<SplashAdBean> = MutableLiveData()
    fun showAd() {
        mRespository.getSplashAd(adInfor)
    }
}