package com.ymars.news.ui.fragment

import android.os.Bundle
import android.os.Message
import androidx.lifecycle.Observer
import com.ymars.news.R
import com.ymars.news.bean.NewsBannerBean
import com.ymars.news.databinding.FragmentNewsMainBinding
import com.ymars.news.viewmodel.NewsMainViewModel
import com.ymars.poj.base.ui.LifecyclerFragment


class NewsMainFragment : LifecyclerFragment<NewsMainViewModel, FragmentNewsMainBinding>() {
    companion object {
        fun newInstance(tag: String) = NewsMainFragment().apply {
            var arguments = Bundle(1).apply {
                putString("tag", tag)
            }
        }

    }

    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.fragment_news_main
    }

    override fun doWork() {
        vm.banner.observe(this, bannerObserver)
        vm.getBanner()
    }

    override fun handlerMsg(msg: Message) {

    }

    private val bannerObserver by lazy {
        Observer<ArrayList<NewsBannerBean>> {
            it?.let {

            }
        }
    }
}