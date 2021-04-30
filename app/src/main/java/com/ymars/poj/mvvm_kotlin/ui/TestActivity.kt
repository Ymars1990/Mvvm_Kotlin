package com.ymars.poj.mvvm_kotlin.ui

import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.ymars.poj.base.ui.BaseActivity
import com.ymars.poj.component.ArouterConstant
import com.ymars.poj.mvvm_kotlin.R
import com.ymars.poj.mvvm_kotlin.databinding.ActivityTestBinding

@Route(path = ArouterConstant.APP_TEST)
class TestActivity : BaseActivity<ActivityTestBinding>() {
    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.activity_test
    }

    override fun doWork() {
        Glide.with(this)
            .load("https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png")
            .into(vb.bannerIv)
        vb.appBarl.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset <= -50) {
                vb.tbTv.visibility = View.VISIBLE
                vb.tb.setBackgroundResource(R.color.white)
                //使用下面两个CollapsingToolbarLayout的方法设置展开透明->折叠时你想要的颜色
//                vb.cstl.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
            } else {
                vb.cstl.setTitle("")
                vb.tbTv.visibility = View.GONE
                vb.tb.setBackgroundResource(R.color.transparent)
            }
        })
    }

    override fun initParams(savedInstanceState: Bundle?) {
        vb = DataBindingUtil.setContentView(this, setLayout(savedInstanceState))
    }

    override fun handlerMsg(msg: Message) {
    }
}